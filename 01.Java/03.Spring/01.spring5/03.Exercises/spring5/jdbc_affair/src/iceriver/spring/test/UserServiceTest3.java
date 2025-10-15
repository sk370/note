package iceriver.spring.test;

import iceriver.spring.service.UserService2;
import iceriver.spring.config.TxConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/17 9:27
 */
class UserServiceTest3 {
    @Test
    void accoutMoney() {
        ApplicationContext context = new AnnotationConfigApplicationContext(TxConfig.class);
        UserService2 userService = context.getBean("userService2", UserService2.class);
        userService.accoutMoney();
    }
}