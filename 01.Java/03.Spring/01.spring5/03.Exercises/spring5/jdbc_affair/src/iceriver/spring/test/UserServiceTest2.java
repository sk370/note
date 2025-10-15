package iceriver.spring.test;

import iceriver.spring.service.UserService;
import iceriver.spring.service.UserService2;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/17 9:27
 */
class UserServiceTest2 {
    @Test
    void accoutMoney() {
        ApplicationContext context = new ClassPathXmlApplicationContext("iceriver_xml.xml");
        UserService2 userService = context.getBean("userService2", UserService2.class);
        userService.accoutMoney();
    }
}