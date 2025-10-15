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


//@WebServlet(name = "LoginServlet", value = "/loginServlet")//整合到UserServlet进行优化，不再使用
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (userService.existUsername(username)) {
            if (userService.login(new User(null, username, password, null)) != null) {
                request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request,response);
            }else {
                request.setAttribute("msg", "密码错误，请确认后登录！");
                request.setAttribute("username", username);
                request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
            }
        }else {
            request.setAttribute("msg", "用户" + username + "不存在！");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }

    }
}
