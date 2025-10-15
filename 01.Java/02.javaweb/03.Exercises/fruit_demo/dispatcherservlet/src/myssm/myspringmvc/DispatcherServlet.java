package myssm.myspringmvc;

import myssm.utils.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能1：完成请求转发
 * 功能2：完成视图渲染
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/22 14:54
 */
@WebServlet("*.do")//注意：这里不需要斜杠，表示拦截所有到 .do 结尾的请求
public class DispatcherServlet extends ViewBaseServlet {
    private Map<String, Object> beanMap = new HashMap<>();

    //在初始化方法中解析 applicationContext.xml 配置文件
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("applicationContext.xml");//1. 加载配置文件
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();//2.
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();//3.
            Document document = documentBuilder.parse(inputStream);//4. 创建document对象

            NodeList beanNodes = document.getElementsByTagName("bean");
            for (int i = 0; i < beanNodes.getLength(); i++) {
                Node beanNode = beanNodes.item(i);
                if(beanNode.getNodeType() == Node.ELEMENT_NODE){
                    Element elementNode = (Element) beanNode;
                    String beanId = elementNode.getAttribute("id");
                    String beanClass = elementNode.getAttribute("class");
                    Object beanObject = Class.forName(beanClass).newInstance();
                    beanMap.put(beanId, beanObject);
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        // 根据请求获取controller ——获得请求的id后，从beanMap中查找对应的类
        // 设：地址为：http://localhost:8080/mvc/fruit.do
        String servletPath = req.getServletPath();//获取当前路径工程名后的地址，带斜杠——/friut.do
        servletPath = servletPath.substring(1);//从下标1取到末尾——fruit.do
        int index = servletPath.indexOf(".do");//获取 .do 的下标值
        servletPath = servletPath.substring(0, index);//获取 fruit

        Object controllerBeanObj = beanMap.get(servletPath);//获得具体的controller对象

        String operate = req.getParameter("operate");
        if(StringUtil.isEmpty(operate)){
            operate = "index";
        }
        //使用反射调用FruitServlet类中的方法
        Method[] declaredMethods = controllerBeanObj.getClass().getDeclaredMethods();
        for(Method m : declaredMethods){
            String methodName = m.getName();
            if (operate.equals(methodName)) {
                try {
                    //1. 统一获取请求参数
                    Parameter[] parameters = m.getParameters();//~从方法获取参数对象数组
                    Object[] parameterValues = new Object[parameters.length];//存放参数值
                    for (int i = 0; i < parameters.length; i++) {
                        Parameter parameter = parameters[i];//~获取参数对象
                        String parameterName = parameter.getName();//~获取参数名
                        if ("request".equals(parameterName) || "req".equals(parameterName)) {
                            parameterValues[i] = req;
                        } else if ("response".equals(parameterName) || "resp".equals(parameterName)) {
                            parameterValues[i] = resp;
                        }else if("session".equals(parameterName)){
                            parameterValues[i] = req.getSession();
                        }else{
                            Object parameterValue = req.getParameter(parameterName);//根据参数名从请求路径获取参数值，这里没有考虑复选框提交的问题【复选框提交的参数会是数组】
                            String typeName = parameter.getType().getName();
                            if(parameterValue != null){
                                if("java.lang.Integer".equals(typeName)){
                                    parameterValue = Integer.parseInt(parameterValue.toString());
                                }
                            }
                            parameterValues[i] = parameterValue;
                        }
                    }
                    //2. controller组件中方法调用
                    m.setAccessible(true);
                    Object methodReturnObj = m.invoke(controllerBeanObj, parameterValues);
                    //3. 视图处理
                    String methodReturnStr = (String) methodReturnObj;
                    if(methodReturnStr.startsWith("redirect:")){
                        String redirectStr = methodReturnStr.substring("redirect:".length());
                        resp.sendRedirect(redirectStr);//3.1重定向到某个请求
                    }else{
                        this.processTemplate(methodReturnStr, req,resp);//3.2转发到某个页面
                    }
                    return;
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
