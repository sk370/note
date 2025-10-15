package iceriver.spring.test;

import iceriver.spring.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/12 8:59
 */
public class TestSpring5 {
    @Test
    public void testSay(){
//        1. 加载配置文件
        BeanFactory context = new ClassPathXmlApplicationContext("iceriver.xml");
//        2. 获取配置文件创建的对象
        User user = context.getBean("user", User.class);
//        user.say();
        System.out.println(user);
    }
}
