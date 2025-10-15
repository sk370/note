package test;

import org.junit.jupiter.api.Test;
import native_.pojo.Book;
import service.BookService;
import service.impl.BookServiceImpl;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/7/5 21:17
 */
class BookServiceTest {
    private BookService bookService = new BookServiceImpl();

    @Test
    void addBook() {
        bookService.addBook(new Book(null, "西游记", "吴承恩", new BigDecimal(56), 100, 100, null));
    }

    @Test
    void deleteBook() {
        bookService.deleteBook(22);
    }

    @Test
    void updateBook() {
        bookService.updateBook(new Book(22, "西游记", "吴承恩+1", new BigDecimal(56), 100, 100, null));
    }

    @Test
    void queryBookById() {
        System.out.println(bookService.queryBookById(25));
    }

    @Test
    void queryBooks() {
        List<Book> books = bookService.queryBooks();
        for(Book book : books){
            System.out.println(book);
        }
    }

    @Test
    void page(){
        System.out.println(bookService.page(1,4));
    }

    @Test
    void pageByPrice(){
        System.out.println(bookService.pageByPrice(1,4, 10, 50));
    }
}