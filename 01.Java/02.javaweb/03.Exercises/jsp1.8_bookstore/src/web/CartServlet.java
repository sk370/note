package web; /**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/7/8 19:55
 */

import com.google.gson.Gson;
import dao.BookDao;
import dao.impl.BookDaoImpl;
import native_.pojo.Book;
import native_.pojo.Cart;
import native_.pojo.CartItem;
import service.BookService;
import service.impl.BookServiceImpl;
import utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CartServlet", value = "/cartServlet")
public class CartServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();
    BookDao bookDao = new BookDaoImpl();

    protected void ajaxAddItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        1. 根据前端传入的id查询指定商品
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        Book book = bookService.queryBookById(id);
//        2. 将查找到的图书转化为cartitem
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice(),book.getStock());
//        3. 将cartitem添加到商品页面
//        3.1 创建唯一的cart
        Cart cart = (Cart) request.getSession().getAttribute("cart");//检查有无session域对象，在该域对象中有无cart对象
        if(cart == null){
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
//        3.2 添加cartitem
        cart.addItem(cartItem);
//        3.3 记录商品名称，当作最后一个添加的商品，反馈到前端页面上
        request.getSession().setAttribute("lastItem", cartItem.getName());
//        4. 重定向回首页
//        response.sendRedirect(request.getHeader("Referer"));//request.getHeader("Referer")可以获取原来的网页地址
//        System.out.println(request.getSession().getAttribute("cart"));
//        4. 给首页返回数据——添加数量和商品名称
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("totalCount", cart.getTotalCount());
        resultMap.put("lastItem", cartItem.getName());
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        response.getWriter().write(json);

    }
/*

    */
/**
     * 加入购物车。上面的方法是对该方法的改进，使用了ajax技术
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     *//*

    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        1. 根据前端传入的id查询指定商品
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        Book book = bookService.queryBookById(id);
//        2. 将查找到的图书转化为cartitem
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice(),book.getStock());
//        3. 将cartitem添加到商品页面
//        3.1 创建唯一的cart
        Cart cart = (Cart) request.getSession().getAttribute("cart");//检查有无session域对象，在该域对象中有无cart对象
        if(cart == null){
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
//        3.2 添加cartitem
        cart.addItem(cartItem);
//        3.3 记录商品名称，当作最后一个添加的商品，反馈到前端页面上
        request.getSession().setAttribute("lastItem", cartItem.getName());
//        4. 重定向回首页
        response.sendRedirect(request.getHeader("Referer"));//request.getHeader("Referer")可以获取原来的网页地址
        System.out.println(request.getSession().getAttribute("cart"));
    }
*/

    /**
     * 删除商品项
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart != null){
            cart.deleteItem(id);
        }
        response.sendRedirect(request.getHeader("Referer"));//request.getHeader("Referer")可以获取原来的网页地址
    }

    /**
     * 清空购物车车
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart != null){
            cart.clear();
        }
        response.sendRedirect(request.getHeader("Referer"));//request.getHeader("Referer")可以获取原来的网页地址
    }

    /**
     * 修改商品数量
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void updateItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        int count = WebUtils.parseInt(request.getParameter("count"), 1);
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart != null){
            cart.updateItem(id, count);
        }
        response.sendRedirect(request.getHeader("Referer"));//request.getHeader("Referer")可以获取原来的网页地址
    }
}
