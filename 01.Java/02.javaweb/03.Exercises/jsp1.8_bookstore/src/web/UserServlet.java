package web; /**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/7/5 14:52
 */

import com.google.gson.Gson;
import native_.pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

//@WebServlet(name = "UserServlet", value = "/userServlet")//
public class UserServlet extends BaseServlet {

    UserService userService = new UserServiceImpl();

    protected void ajaxExistUserName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("username");
        boolean usernameResult = userService.existUsername(userName);
//      将返回结果封装成MAp对象
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("usernameResult", usernameResult);
//        将map对象转换为JSON字符串准备传递给前端
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
//      服务区把json字符串发送给客户端
        response.getWriter().write(json);
    }

    /**
     * 用户登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        获取谷歌验证码
        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");

        if(token.equalsIgnoreCase(code)){
            if (userService.existUsername(username)) {
                request.setAttribute("msg", username + "已经存在！");
                request.setAttribute("username", username);
                request.setAttribute("password", password);
                request.setAttribute("email", email);
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
            }else {
                userService.registUser(new User(null, username, password, email));
                request.getRequestDispatcher("pages/user/regist_success.jsp").forward(request,response);//如果不以/开头，按理来说不能跳转，但是为什么路径没错？
            }

        }else{
            request.setAttribute("msg", "验证码错误！");
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("email", email);
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
        }
    }

    /**
     * 用户注册
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (userService.existUsername(username)) {
            User loginUser = userService.login(new User(null, username, password, null));
            if ( loginUser != null) {
                request.getSession().setAttribute("user", loginUser);//保存已登录用户的信息到session中
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

    /**
     * 登出当前用户
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();//销毁保存已登录用户的信息的session
        response.sendRedirect(request.getContextPath());//销毁session后重定向至首页
    }

}
