package iceriver.spring.test;

import iceriver.spring.pojo.Book;
import iceriver.spring.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/16 16:55
 */
class BookDaoImplTest {

    @Test
    void add() {
        ApplicationContext context = new ClassPathXmlApplicationContext("iceriver.xml");
        BookService bookService = context.getBean("bookService", BookService.class);
        Book book = new Book();
        book.setUserId(1);
        book.setUserName("java");
        book.setUstatus("a");
        bookService.addBook(book);
    }
    @Test
    void update() {
        ApplicationContext context = new ClassPathXmlApplicationContext("iceriver.xml");
        BookService bookService = context.getBean("bookService", BookService.class);
        Book book = new Book();
        book.setUserId(1);
        book.setUserName("java");
        book.setUstatus("b");
        bookService.updateBook(book);
    }
    @Test
    void delete() {
        ApplicationContext context = new ClassPathXmlApplicationContext("iceriver.xml");
        BookService bookService = context.getBean("bookService", BookService.class);
        bookService.deleteBook(1);
    }
    @Test
    void findCount() {
        ApplicationContext context = new ClassPathXmlApplicationContext("iceriver.xml");
        BookService bookService = context.getBean("bookService", BookService.class);
        bookService.findCount();
    }
    @Test
    void findOne() {
        ApplicationContext context = new ClassPathXmlApplicationContext("iceriver.xml");
        BookService bookService = context.getBean("bookService", BookService.class);
        bookService.findOne(500);
    }

    @Test
    void findAll() {
        ApplicationContext context = new ClassPathXmlApplicationContext("iceriver.xml");
        BookService bookService = context.getBean("bookService", BookService.class);
        bookService.findAll();
    }
    @Test
    void batchAdd() {
        ApplicationContext context = new ClassPathXmlApplicationContext("iceriver.xml");
        BookService bookService = context.getBean("bookService", BookService.class);
        List<Object[]> batchArgs = new ArrayList<>();
        Object[] book1 = {3, "java", "a"};
        Object[] book2 = {4, "java", "a"};
        Object[] book3 = {5, "java", "a"};
        batchArgs.add(book1);
        batchArgs.add(book2);
        batchArgs.add(book3);
        bookService.batchAdd(batchArgs);
    }
    @Test
    void batchUpdate() {
        ApplicationContext context = new ClassPathXmlApplicationContext("iceriver.xml");
        BookService bookService = context.getBean("bookService", BookService.class);
        List<Object[]> batchArgs = new ArrayList<>();
        Object[] book1 = {"前端", "a",3};
        Object[] book2 = {"mysql", "a",4};
        Object[] book3 = {"web", "a",5};
        batchArgs.add(book1);
        batchArgs.add(book2);
        batchArgs.add(book3);
        bookService.batchUpdate(batchArgs);
    }
    @Test
    void batchDelete() {
        ApplicationContext context = new ClassPathXmlApplicationContext("iceriver.xml");
        BookService bookService = context.getBean("bookService", BookService.class);
        List<Object[]> batchArgs = new ArrayList<>();
        Object[] book1 = {3};
        Object[] book2 = {4};
        Object[] book3 = {5};
        batchArgs.add(book1);
        batchArgs.add(book2);
        batchArgs.add(book3);
        bookService.batchDelete(batchArgs);
    }
}