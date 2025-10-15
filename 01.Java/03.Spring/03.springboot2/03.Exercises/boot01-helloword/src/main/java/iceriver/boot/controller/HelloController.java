package iceriver.boot.controller;

import iceriver.boot.bean.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/8/3 20:12
 */
@RestController
public class HelloController {
    @Autowired
    Car car;
    @RequestMapping("/hello")
    public String handle01(){
        return "Hello, Spring Boot 2!";
    }
    @RequestMapping("/car")
    public String handle02(){
        return car.toString();
    }
}