package web;
/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/7/5 21:45
 */

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
import java.util.List;

@WebServlet(name = "BookServlet", value = "/manage/bookServlet")
public class BookServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        1.将获取到的请求参数封装成Book对象
        Book book = WebUtils.copyParamToBean(new Book(), request.getParameterMap());
//        2.调用BookService的addBook()
        bookService.addBook(book);
//        3.页面跳转到图书列表页面——重定向
//        request.getRequestDispatcher("/manage/bookServlet?action=query").forward(request,response);/有bug，由于请求转发后台完成，前台的地址不会发生变化，相当于还是【manage/bookServlet】，按F5刷新时，会重新发一次添加的请求，而浏览器会记录请求的参数，导致重复添加数据。使用重定向可以解决此问题——使浏览器发生变化，页面查询由浏览器请求决定。
        response.sendRedirect(request.getContextPath() + "/manage/bookServlet?action=page&pageNo=" + request.getParameter("pageNo"));

    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        1. 获取请求参数：delete和id——delete在doget方法中已经请求了
        String id = request.getParameter("id");
//        2. 调用BookServlet的deleteBook()
        bookService.deleteBook(Integer.valueOf(id));
//        3. 重定向回图书管理页面
        response.sendRedirect(request.getContextPath() + "/manage/bookServlet?action=page&pageNo=" + request.getParameter("pageNo"));

    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        1.读取表单中的修改信息，将获取到的请求参数封装成Book对象
        Book book = WebUtils.copyParamToBean(new Book(), request.getParameterMap());
//        2.调用BookService的updateBook()
        bookService.updateBook(book);
//        3.页面跳转到图书列表页面——重定向
//        request.getRequestDispatcher("/manage/bookServlet?action=query").forward(request,response);/有bug，由于请求转发后台完成，前台的地址不会发生变化，相当于还是【manage/bookServlet】，按F5刷新时，会重新发一次修改的请求，而浏览器会记录请求的参数，导致重复修改数据。使用重定向可以解决此问题——使浏览器发生变化，页面查询由浏览器请求决定。
        response.sendRedirect(request.getContextPath() + "/manage/bookServlet?action=page&pageNo=" + request.getParameter("pageNo"));
    }

    protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> books = bookService.queryBooks();//1. 从数据库查找数据
        request.setAttribute("books", books);//2. 将查找到的数据存放到request域对象
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);//3. 将request域对象转发到前端页面，这里url开头必须写上/
    }

    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        1. 获取请求参数：getBook和id——getBook在doget方法中已经请求了
        String id = request.getParameter("id");
//        2. 调用BookServlet的queryBookById()
        Book book = bookService.queryBookById(WebUtils.parseInt(id, 0));
//        3. 将查询到的图书信息保存到request域对象中
        request.setAttribute("book", book);
//        4. 请求转发到book_edit.jsp页面 再把域对象的数据会写到jsp他页面的表单中
//        response.sendRedirect(request.getContextPath() + "/manager/book_edit.jsp");//不能使用重定向，这是两次request请求，设置的域对象会不存在
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
    }

    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        1.获取请求参数pageNo和pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
//        2. 调用bookService的page(pageNo, pageSize),获取当前页的page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("manage/bookServlet?action=page");
//        3. 将page对象保存到request域对象中
        request.setAttribute("page", page);
//        4. 请求转发到book_manager.jsp页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);

    }

}
