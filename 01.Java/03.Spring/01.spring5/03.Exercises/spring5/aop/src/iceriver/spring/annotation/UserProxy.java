package iceriver.spring.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/16 14:15
 */
@Component
@Aspect
public class UserProxy {
//    相同切入点抽取
    @Pointcut(value = "execution(* iceriver.spring.annotation.User.add(..))")
    public void pointDemo(){

    }

    @Before(value = "pointDemo()")
    public void before(){//前置通知
        System.out.println("UserProxy before");
    }
    @AfterReturning(value = "execution(* iceriver.spring.annotation.User.add(..))")
    public void afterReturning(){//后置通知
        System.out.println("afterReturning");
    }
    @After(value = "execution(* iceriver.spring.annotation.User.add(..))")
    public void after(){//最终通知
        System.out.println("after");
    }
    @AfterThrowing(value = "execution(* iceriver.spring.annotation.User.add(..))")
    public void afterThrowing(){//异常通知
        System.out.println("afterThrowing");
    }
    @Around(value = "execution(* iceriver.spring.annotation.User.add(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {//环绕通知
        System.out.println("around之前");
        proceedingJoinPoint.proceed();//被增强的方法执行，如果不写这句，则被代理的类中的方法、以及前置通知不会被执行
        System.out.println("around之后");
    }
}
