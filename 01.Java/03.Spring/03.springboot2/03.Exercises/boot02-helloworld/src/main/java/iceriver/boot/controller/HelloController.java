package iceriver.boot.controller;

import iceriver.boot.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/8/4 11:13
 */
@RestController
public class HelloController {

    @Autowired
    Person person;

    @RequestMapping("/person")
    public Person person(){

//        String userName = person.getUserName();
//        System.out.println(userName);
        return person;
    }
}
