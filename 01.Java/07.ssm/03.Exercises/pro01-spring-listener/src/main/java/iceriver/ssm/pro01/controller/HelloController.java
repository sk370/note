package iceriver.ssm.pro01.controller;

import iceriver.ssm.pro01.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/8/1 15:43
 */
@Controller
public class HelloController {
    @Autowired
    private HelloService helloService;
}
