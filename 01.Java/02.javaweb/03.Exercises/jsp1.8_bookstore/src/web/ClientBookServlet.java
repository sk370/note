package web;

import native_.pojo.Book;
import native_.pojo.Page;
import service.BookService;
import service.impl.BookServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 首页书单
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/7/7 8:12
 */
@WebServlet(name = "ClientBookServlet", value = "/client/clientBookServlet")
public class ClientBookServlet extends BaseServlet{
    private BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        1.获取请求参数pageNo和pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
//        2. 调用bookService的page(pageNo, pageSize),获取当前页的page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("client/clientBookServlet?action=page");
//        3. 将page对象保存到request域对象中
        request.setAttribute("page", page);
//        4. 请求转发到index.jsp页面
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
    }


    protected void pageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        1.获取请求参数pageNo和pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(request.getParameter("min"), 0);
        int max = WebUtils.parseInt(request.getParameter("max"), Integer.MAX_VALUE);

//        2. 调用bookService的page(pageNo, pageSize),获取当前页的page对象
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, min, max);

        StringBuilder sb = new StringBuilder("client/clientBookServlet?action=pageByPrice");
        if(request.getParameter("min") != null){
            sb.append("&min=").append(request.getParameter("min"));
        }
        if(request.getParameter("max") != null){
            sb.append("&max=").append(request.getParameter("max"));
        }
        page.setUrl(sb.toString());
//        3. 将page对象保存到request域对象中
        request.setAttribute("page", page);
//        4. 请求转发到book_manager.jsp页面
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);

    }
}
