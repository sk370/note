package iceriver.spring.test;

import iceriver.spring.live.Orders;
import iceriver.spring.pojo.Animal;
import iceriver.spring.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/15 12:21
 */
class OrdersTest {
    @Test
    public void test(){

//        ApplicationContext context =
//                new ClassPathXmlApplicationContext("bean4.xml");
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("bean3.xml");
        System.out.println(context.getClass());
        System.out.println(context.getClassLoader());
        Orders orders = context.getBean("orders", Orders.class);
        System.out.println("第四步 获取创建bean实例对象");
        System.out.println(orders);
        //手动让bean实例销毁
        context.close();
    }
    @Test
    public void test2(){

//        ApplicationContext context =
//                new ClassPathXmlApplicationContext("bean4.xml");
        ApplicationContext context =
                new ClassPathXmlApplicationContext("bean3.xml");

        User user = context.getBean("user", User.class);
//        Animal animal = context.getBean("user", Animal.class);//报错
        System.out.println(user);
//        System.out.println(animal);//报错
        System.out.println(user.getClass());
    }
}