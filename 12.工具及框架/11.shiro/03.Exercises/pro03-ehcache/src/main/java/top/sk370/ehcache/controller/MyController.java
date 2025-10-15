package top.sk370.ehcache.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className MyController
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/01 19:15
 */
@Controller
@RequestMapping("myController")
public class MyController {

    /**
     * 访问登录页面
     */
    @GetMapping("login")
    public String login(){
        return "login";
    }

    /**
     * 执行登录
     */
    @GetMapping("userLogin")
    @ResponseBody//让登录失败时返回字符串，而不是thymeleaf页面
    public String userLogin(String name, String pwd, HttpSession session, @RequestParam(defaultValue =
            "false")boolean rememberMe) {
        //1 获取 Subject 对象
        Subject subject = SecurityUtils.getSubject();
        //2 封装请求数据到 token 对象中
        AuthenticationToken token = new UsernamePasswordToken(name, pwd,rememberMe);
        //3 调用 login 方法进行登录认证
        try {
            subject.login(token);
            session.setAttribute("user", token.getPrincipal().toString());
            return "main";//跳转页面
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("登录失败");
            return "登录失败";
        }
    }

    /**
     * 访问该页面，测试有无相应角色
     * @return
     */
    @RequiresRoles("admin")
    @GetMapping("userLoginRoles")
    @ResponseBody//由于引入了thymelaf，这里不写ResponseBody则相当于返回页面，加了相当于在页面返回内容
    public String userLoginRoles() {
        System.out.println("登录认证验证角色");
        return "验证角色成功";
    }

    /**
     * 访问该页面，测试有无相应权限
     * @return
     */
    @RequiresPermissions("user:delete")
    @GetMapping("userPermissions")
    @ResponseBody
    public String userLoginPermissions() {
        System.out.println("登录认证验证权限");
        return "验证权限成功";
    }
}
