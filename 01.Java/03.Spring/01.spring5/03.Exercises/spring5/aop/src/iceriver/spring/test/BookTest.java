package iceriver.spring.test;

import iceriver.spring.xml.Book;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/16 15:32
 */
class BookTest {

    @Test
    void buy() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean_xml.xml");
        Book book = context.getBean("book", Book.class);
        book.buy();
    }
}