package iceriver.spring.test;

import iceriver.spring.pojo.Emp;
import iceriver.spring.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/15 8:59
 */
class EmpTest {
    @Test
    public void add(){
        ApplicationContext context = new ClassPathXmlApplicationContext("iceriver.xml");
        Emp emp = context.getBean("emp", Emp.class);
        emp.add();
    }
}