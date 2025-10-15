package web; /**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/7/3 20:07
 */

import native_.pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "RegistServlet", value = "/registServlet")//整合到UserServlet进行优化，不再使用
public class RegistServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");
//        暂时未实现生成验证码
        if("abcde".equalsIgnoreCase(code)){
            if (userService.existUsername(username)) {
                request.setAttribute("msg", username + "已经存在！");
                request.setAttribute("username", username);
                request.setAttribute("password", password);
                request.setAttribute("email", email);
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
            }else {
                userService.registUser(new User(null, username, password, email));
                request.getRequestDispatcher("pages/user/regist_success.jsp").forward(request,response);
            }

        }else{
            request.setAttribute("msg", "验证码错误！");
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("email", email);
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
        }
    }
}
