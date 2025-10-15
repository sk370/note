package fruit.servlets;

import fruit.dao.FruitDAO;
import fruit.dao.impl.FruitDAOImpl;
import fruit.pojo.Fruit;
import myssm.myspringmvc.ViewBaseServlet;
import myssm.utils.StringUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/22 13:13
 */
@WebServlet("/fruit.do")
public class FruitServlet extends ViewBaseServlet {
    FruitDAO fruitDAO = new FruitDAOImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        String operate = req.getParameter("operate");
        if(StringUtil.isEmpty(operate)){
            operate = "index";
        }
        //使用反射调用FruitServlet类中的方法
        Method[] declaredMethods = this.getClass().getDeclaredMethods();
        for(Method m : declaredMethods){
            String methodName = m.getName();
            if (operate.equals(methodName)) {
                try {
                    m.invoke(this, req, resp);
                    return;
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        throw new RuntimeException("operate非法！");
    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int pageNo = 1;
        HttpSession session = request.getSession();

        String oper = request.getParameter("oper");
        String keywords;
        if(StringUtil.isNotEmpty(oper) && "search".equals(oper)){//判断是查询请求，还是翻页请求
            keywords = request.getParameter("keyword");
            if(StringUtil.isEmpty(keywords)){
                keywords = "";
            }
            session.setAttribute("keywords", keywords);
        }else{
            String pageNoStr = request.getParameter("pageNo");
            if(StringUtil.isNotEmpty(pageNoStr)){
                pageNo = Integer.parseInt(pageNoStr);
            }
            Object keywordsObj = session.getAttribute("keywords");
            if(keywordsObj != null){
                keywords = (String)keywordsObj;
            }else{
                keywords = "";
            }
        }

//        List<Fruit> fruitList = fruitDAO.getFruitList();//不分页显示
//        List<Fruit> fruitList = fruitDAO.getFruitListByPage(pageNo);//分页显示，显示第?页，不带关键词查询
        List<Fruit> fruitList = fruitDAO.getFruitListByPage(keywords, pageNo);//分页显示，显示第?页，带关键词查询
//        int fruitCount = fruitDAO.getFruitCount();//获取总结记录条数，不带关键词查询
        int fruitCount = fruitDAO.getFruitCount(keywords);//获取总结记录条数，不带关键词查询
        int totalPage = (fruitCount + 5 - 1) / 5;//总页数
        //保存到session作用域
        session.setAttribute("pageNo", pageNo);
        session.setAttribute("fruitList", fruitList);
        session.setAttribute("totalPage",totalPage);
        //此处的视图名称是 index
        //那么thymeleaf会将这个 逻辑视图名称 对应到 物理视图 名称上去
        //逻辑视图名称 ：   index
        //物理视图名称 ：   view-prefix + 逻辑视图名称 + view-suffix
        //所以真实的视图名称是：      /       index       .html
        super.processTemplate("index", request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        Integer price = Integer.parseInt(priceStr);
        String fcountStr = request.getParameter("fcount");
        Integer fcount = Integer.parseInt(fcountStr);
        String remark = request.getParameter("remark");

        boolean flag = fruitDAO.addFruit(new Fruit(0 , fname , price , fcount , remark));

        System.out.println(flag ? "添加成功！" : "添加失败！");
        response.sendRedirect("fruit.do");
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fidStr = req.getParameter("fid");
        if(StringUtil.isNotEmpty(fidStr)){
            int fid = Integer.parseInt(fidStr);
            fruitDAO.delFruitById(fid);
            resp.sendRedirect("fruit.do");
        }
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        String fidStr = req.getParameter("fid");
        if (StringUtil.isNotEmpty(fidStr)) {
            int fid = Integer.parseInt(fidStr);
            Fruit fruit = fruitDAO.getFruitById(fid);
            req.setAttribute("fruit", fruit);
            processTemplate("edit", req, resp);
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fname = req.getParameter("fname");
        Integer price = Integer.parseInt(req.getParameter("price"));
        Integer fcount = Integer.parseInt(req.getParameter("fcount"));
        Integer fid = Integer.parseInt(req.getParameter("fid"));
        String remark = req.getParameter("remark");

        fruitDAO.updateFruit(new Fruit(fid,fname,price,fcount,remark));
        resp.sendRedirect("fruit.do");
    }
}
