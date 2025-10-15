package web; /**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/7/5 15:33
 */

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

//@WebServlet(name = "BaseServlet", value = "/BaseServlet")//不需要访问该页面
public abstract class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        解决post请求中文乱码问题
        request.setCharacterEncoding("UTF-8");
//      解决响应的中文乱码
        response.setContentType("text/html;charset=utf-8");

        String action = request.getParameter("action");

        try {
//            Method declaredMethod = UserServlet.class.getDeclaredMethod(action, request.getClass(), response.getClass());//由于doget方法要使用dopost方法，所以这里反射不能用类名.class写死，用this.getclass()的方式
//            Method declaredMethod = this.getClass().getDeclaredMethod(action, request.getClass(), response.getClass());             //为什么和下面一行不等价？会报没有这样方法的异常？因为htprequest和hettpservletresponse对原始的类进行了封装
            Method declaredMethod = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            declaredMethod.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();//由于事务管理遇到其它地方的异常不会做处理，所以这里也用捕获异常的的方式，将异常抛给事务管理的类【Filter过滤器】，由他去处置
        }

        /*使用反射代替判断
        if("login".equals(action)){
            login(request,response);
        }else if("regist".equals(action)){
            regist(request,response);
        }*/
    }
}
