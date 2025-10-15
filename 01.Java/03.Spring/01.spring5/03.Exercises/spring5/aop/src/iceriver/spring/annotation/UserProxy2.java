package iceriver.spring.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/16 14:15
 */
@Component
@Aspect
@Order(10)
public class UserProxy2 {
    @Before(value = "execution(* iceriver.spring.annotation.User.add(..))")
    public void before(){//前置通知
        System.out.println("UserProxy2 before");
    }
}
