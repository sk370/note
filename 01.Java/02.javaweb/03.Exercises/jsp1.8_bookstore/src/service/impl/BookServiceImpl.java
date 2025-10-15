package service.impl;

import dao.BookDao;
import dao.impl.BookDaoImpl;
import native_.pojo.Book;
import native_.pojo.Page;
import service.BookService;

import java.util.List;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/7/5 21:08
 */
public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();


    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBook(Integer id) {
        bookDao.deleteBook(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();

        page.setPageSize(pageSize);//设置每页显示数量

        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);//设置总记录数

        Integer pageTotal = pageTotalCount / pageSize;//求总页码
        if(pageTotalCount % pageSize > 0){
            pageTotal++;
        }
        page.setPageTotal(pageTotal);//设置总页码

        page.setPageNo(pageNo);//设置当前第几页

        int begin = (page.getPageNo() - 1) * pageSize;//设置当前页数据的第一条显示位置

        List<Book> items = bookDao.queryForPageItems(begin, pageSize);//求当前页数据(图书清单)

        page.setItems(items);//设置当前页数据

        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<>();

        page.setPageSize(pageSize);//设置每页显示数量

        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min, max);
        page.setPageTotalCount(pageTotalCount);//设置总记录数

        Integer pageTotal = pageTotalCount / pageSize;//求总页码
        if(pageTotalCount % pageSize > 0){
            pageTotal++;
        }
        page.setPageTotal(pageTotal);//设置总页码

        page.setPageNo(pageNo);//设置当前第几页

        int begin = (page.getPageNo() - 1) * pageSize;//设置当前页数据的第一条显示位置

        List<Book> items = bookDao.queryForPageItemsByPrice(begin, pageSize, min, max);//求当前页数据(图书清单)

        page.setItems(items);//设置当前页数据

        return page;
    }
}
