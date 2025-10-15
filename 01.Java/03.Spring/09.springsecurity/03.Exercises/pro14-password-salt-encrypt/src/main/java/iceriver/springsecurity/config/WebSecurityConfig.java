package iceriver.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

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
    @Autowired
    private DataSource dataSource;
    @Autowired
    private MyUserDetailsService myUserDetailsService;
//    @Autowired
//    private MyPasswordEncoder myPasswordEncoder;
    @Bean
    BCryptPasswordEncoder getBCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private BCryptPasswordEncoder myPasswordEncoder;

    @Override
    protected void configure(HttpSecurity security) throws Exception {

        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);

        //super.configure(security); 注释掉将取消父类方法中的默认规则
        security.authorizeRequests()			                // 对请求进行授权
                .antMatchers("/index.jsp")		// 针对/index.jsp路径进行授权
                .permitAll()					                // 可以无条件访问
                .antMatchers("/layui/**")		// 针对/layui目录下所有资源进行授权
                .permitAll()					                // 可以无条件访问

                .antMatchers("/level1/**")		// 针对/level1/**路径设置访问要求
                .hasRole("学徒")					// 要求用户具备“学徒”角色才可以访问
                .antMatchers("/level2/**")		// 针对/level2/**路径设置访问要求
                .hasAuthority("内门弟子")			// 要求用户具备“内门弟子”权限才可以访问

                .and()
                .authorizeRequests()			// 对请求进行授权
                .anyRequest()					// 任意请求
                .authenticated()				// 需要登录以后才可以访问
                .and()
                .formLogin()					// 使用表单形式登录
                .loginPage("/index.jsp")		// 指定登录页面（如果没有指定会访问SpringSecurity自带的登录页）
                // 指定登录页的同时会影响到：“提交登录表单的地址”、“退出登录地址”、“登录失败地址”
                // loginProcessingUrl()方法指定了登录地址，就会覆盖loginPage()方法中设置的默认值/index.jsp
                .loginProcessingUrl("/do/login.html")	// 指定form表单提交的地址

                .usernameParameter("loginAcct") // 定制登录账号的请求参数名
                .passwordParameter("userPswd") // 定制登录密码的请求参数名
                .defaultSuccessUrl("/main.html") //设置登录成功后默认前往的 URL 地址

                .and()
//                .csrf()
//                .disable()//禁用csrf功能
                .logout()//开启退出功能
                .logoutUrl("/do/logout.html")//退出请求地址
                .logoutSuccessUrl("/index.jsp")//退出后展示地址

                . and()
                .exceptionHandling()					// 指定异常处理器
                // .accessDeniedPage("/to/no/auth/page.html")	// 方式一：访问被拒绝时前往的页面
                //方式二：
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest request, HttpServletResponse response,
                                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
                        request.setAttribute("message", "抱歉！您无法访问这个资源！☆☆☆");
                        request.getRequestDispatcher("/WEB-INF/views/no_auth.jsp").forward(request, response);
                    }
                })

                .and()
                .rememberMe()//记录等级状态

                .tokenRepository(tokenRepository);//开启令牌仓库功能
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        //super.configure(auth); 一定要禁用默认规则
//        builder.inMemoryAuthentication()
//                .withUser("tom").password("123123") //设置账号密码
//                .roles("ADMIN","学徒")				// 指定当前用户的角色
//                .and()
//                .withUser("jerry")			// 指定账号
//                .password("123123")			// 指定密码
//                .authorities("UPDATE","内门弟子");		// 指定当前用户的权限

        builder
                .userDetailsService(myUserDetailsService)//权限校验
                .passwordEncoder(myPasswordEncoder);//登录密码加密与数据库比较
    }
}
