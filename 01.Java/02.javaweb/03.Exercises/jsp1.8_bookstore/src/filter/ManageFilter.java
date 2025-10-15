package filter;
/**
 * 没有登录不让看manager下面的页面内容
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/11 17:24
 */

import native_.pojo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ManageFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if(user == null){
            httpServletRequest.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);//跳转
        }else {
            chain.doFilter(request,response);//放行
        }
    }
}
