package iceriver.spring.test;

import iceriver.spring.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;

import java.lang.management.PlatformLoggingMXBean;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/17 9:27
 */
class UserServiceTest {
    @Test
    void accoutMoney() {
        ApplicationContext context = new ClassPathXmlApplicationContext("iceriver.xml");
        UserService userService = context.getBean("userService", UserService.class);
        userService.accoutMoney();
    }
}