package iceriver.springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className WebSecurityConfig
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/09/04 10:03
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity security) throws Exception {
        //super.configure(security); 注释掉将取消父类方法中的默认规则
        security.authorizeRequests()			                // 对请求进行授权
                .antMatchers("/index.jsp")		// 针对/index.jsp路径进行授权
                .permitAll()					                // 可以无条件访问
                .antMatchers("/layui/**")		// 针对/layui目录下所有资源进行授权
                .permitAll()					                // 可以无条件访问

                .and()
                .authorizeRequests()			// 对请求进行授权
                .anyRequest()					// 任意请求
                .authenticated()				// 需要登录以后才可以访问
                .and()
                .formLogin()					// 使用表单形式登录
                .loginPage("/index.jsp")		// 指定登录页面（如果没有指定会访问SpringSecurity自带的登录页）
                // 指定登录页的同时会影响到：“提交登录表单的地址”、“退出登录地址”、“登录失败地址”
                // loginProcessingUrl()方法指定了登录地址，就会覆盖loginPage()方法中设置的默认值/index.jsp
                .loginProcessingUrl("/do/login.html");	// 指定form表单提交的地址
    }
}
