package web;

import native_.pojo.Cart;
import native_.pojo.User;
import service.OrderService;
import service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/11 13:15
 */

@WebServlet(name = "OrderServlet", value = "/orderServlet")
public class OrderServlet extends BaseServlet{
    private OrderService orderService = new OrderServiceImpl();
    /**
     * 生成订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1. 获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");//cartservlet中给session设置的cart
//        2. 获取用户id
        User user = (User) req.getSession().getAttribute("user");//userservlet中给session设置的cart
        if(user == null){//判断用户是否登录
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        Integer userId = user.getId();
//        3. 生成订单
        String orderId = orderService.createOrder(cart, userId);

        req.getSession().setAttribute("orderId", orderId);
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");//为什么要用request域对象和请求转发的方式，不会有刷新浏览器重复提交的bug吗？

    }
}
