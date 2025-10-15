package iceriver.spring.test;

import iceriver.spring.collectiontype.Student;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/15 10:17
 */
class StudentTest {

    @Test
    void test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        Student stu = context.getBean("stu", Student.class);
        stu.test();
    }
}