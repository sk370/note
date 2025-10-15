package test;

import dao.BookDao;
import dao.impl.BookDaoImpl;
import org.junit.jupiter.api.Test;
import native_.pojo.Book;
import native_.pojo.Page;

import java.math.BigDecimal;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/7/5 17:49
 */
class BookDaoTest {
    private BookDao bookDao = new BookDaoImpl();

    @Test
    void addBook() {
        bookDao.addBook(new Book(null, "百年孤独", "马尔克斯", new BigDecimal(120), 1000, 100, null));
    }

    @Test
    void deleteBook() {
        bookDao.deleteBook(21);
    }

    @Test
    void updateBook() {
        bookDao.updateBook(new Book(22, "百年孤独", "马尔克斯", new BigDecimal(12), null, 100, null));
    }

    @Test
    void queryBookById() {
        System.out.println(bookDao.queryBookById(23));
    }

    @Test
    void queryBooks() {
        System.out.println("中国");
        for (Book book : bookDao.queryBooks()) {
            System.out.println(book);
        }
    }

    @Test
    void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    void queryForPageItems() {
        for (Book book : bookDao.queryForPageItems(1, 4)) {
            System.out.println(book);
        }
    }


    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(10, 50));
    }

    @Test
    public void queryForPageItemsByPrice() {
        for (Book book : bookDao.queryForPageItemsByPrice(0, Page.PAGE_SIZE, 10, 50)) {
            System.out.println(book);
        }
    }
}