package iceriver.springmvc.controller;

import iceriver.springmvc.bean.User;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/28 13:34
 */
@Controller
public class HttpController {
    @RequestMapping("/testRequestBody")
    public String testRequestBody(@RequestBody String requestBody){
        System.out.println("requestBody" + requestBody);
        return "success";
    }
    @RequestMapping("/testRequestEntity")
    public String testRequestEntity(RequestEntity<String> requestEntity){
        //当前requestEnity表示整个请求报文的信息
        System.out.println("请求头："+requestEntity.getHeaders());
        System.out.println("请求体："+requestEntity.getBody());
        return "success";
    }
    @RequestMapping("/testResponse")
    public void testResponse(HttpServletResponse response) throws IOException {
        response.getWriter().print("hello,response");
    }

    @RequestMapping(value = "/testResponseBody", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String testResponseBody(){
        return "成功";
    }
    @RequestMapping(value = "/testResponseUser")
    @ResponseBody
    public User testResponseUser(){
        return new User(1001, "admin", "123456", 23, "男");
    }

    @RequestMapping("/testAxios")
    @ResponseBody
    public String testAxios(String username, String password){
        System.out.println(username+","+password);
        return "hello,axios";
    }
}
