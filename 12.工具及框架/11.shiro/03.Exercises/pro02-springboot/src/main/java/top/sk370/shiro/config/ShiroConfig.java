package top.sk370.shiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.sk370.shiro.realm.MyRealm;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className ShiroConfig
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/10/01 19:11
 */
@Configuration
public class ShiroConfig {
    @Autowired
    private MyRealm myRealm;

    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }

    //配置 SecurityManager
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager() {
        //1 创建 defaultWebSecurityManager 对象
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //2 创建加密对象，并设置相关属性
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        //2.1 采用 md5 加密
        matcher.setHashAlgorithmName("md5");
        //2.2 迭代加密次数
        matcher.setHashIterations(3);
        //3 将加密对象存储到 myRealm 中
        myRealm.setCredentialsMatcher(matcher);
        //4 将 myRealm 存入 defaultWebSecurityManager 对象
        defaultWebSecurityManager.setRealm(myRealm);
        //4.5 设置 rememberMe
        defaultWebSecurityManager.setRememberMeManager(rememberMeManager());
        //5 返回
        return defaultWebSecurityManager;
    }

    //创建 Shiro 的 cookie 管理对象
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey("1234567890987654".getBytes());
        return cookieRememberMeManager;
    }

    //cookie 属性设置——记住我功能
    public SimpleCookie rememberMeCookie() {
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        //设置跨域
        //cookie.setDomain(domain);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(30 * 24 * 60 * 60);
        return cookie;
    }

//    //多个realm的配置
//    @Bean
//    public DefaultWebSecurityManager defaultWebSecurityManager() {
//        //1 创建 defaultWebSecurityManager 对象
//        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
//        //2 创建认证对象，并设置认证策略
//        ModularRealmAuthenticator modularRealmAuthenticator = new ModularRealmAuthenticator();
//        modularRealmAuthenticator.setAuthenticationStrategy(new AllSuccessfulStrategy());
//        defaultWebSecurityManager.setAuthenticator(modularRealmAuthenticator);
//        //3 封装 myRealm 集合
//        List<Realm> list = new ArrayList<>();
//        list.add(myRealm);
//        list.add(myRealm2);
//        //4 将 myRealm 存入 defaultWebSecurityManager 对象
//        defaultWebSecurityManager.setRealms(list);
//        //5 返回
//        return defaultWebSecurityManager;
//    }

    //配置 Shiro 内置过滤器拦截范围
    @Bean
    public DefaultShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
        //设置不认证可以访问的资源
        definition.addPathDefinition("/myController/userLogin", "anon");
        definition.addPathDefinition("/login", "anon");
        //设置登出过滤器，设置了登出过滤器，就不用设置handler方法了
        definition.addPathDefinition("/logout", "logout");
        //设置需要进行登录认证的拦截范围
        definition.addPathDefinition("/**", "authc");
        //rememberMe的用户无需登录
        definition.addPathDefinition("/**","user");
        return definition;
    }
}
