package iceriver.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    //  通过@RequestMapping注解，可以通过请求路径匹配要处理的具体的请求
    //  /表示的当前工程的上下文路径
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/target")
    public String toTarget(){
        return "target";
    }
    @RequestMapping("/testForward")
    public String testForward(){
        return "forward:/target";
    }
    @RequestMapping("/testRedirect")
    public String testRedirect(){
        return "redirect:/target";
    }
    @RequestMapping("/?a/testAnt")
    public String testAnt(){
        return "target";
    }
    @RequestMapping("/param")
    public String testParam(String username, String password, String hobby){
        System.out.println("username:"+username+",password:"+password+",hobby:"+ hobby);
        return "param";
    }

}
