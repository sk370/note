package iceriver.spring.test;

import iceriver.spring.collectiontype.Book;
import iceriver.spring.collectiontype.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/15 10:55
 */
class BookTest {

    @Test
    void test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
        Book book = context.getBean("book", Book.class);
        book.test();
    }
}