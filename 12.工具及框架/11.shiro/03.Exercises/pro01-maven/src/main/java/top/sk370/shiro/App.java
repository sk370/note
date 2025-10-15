package top.sk370.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class App {
    public static void main(String[] args) {
        // 1 初始化获取 SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //2 获取 Subject 对象
        Subject subject = SecurityUtils.getSubject();
        //3 创建 token 对象， web 应用用户名密码从页面传递
        AuthenticationToken token = new UsernamePasswordToken("zhangsan", "z3");
        //4 完成登录
        try {
            subject.login(token);
            System.out.println("登录成功");
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户不存在");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误");
        } catch (AuthenticationException ae) {
            //unexpected condition? error?
        }
    }
}
