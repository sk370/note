package iceriver.springsecurity.config;

import iceriver.springsecurity.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className MyUserDetailsService
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/09/04 16:01
 */
@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1.使用 SQL 语句根据用户名查询用户对象
        System.out.println(username);
        String sql = "SELECT id,loginAcct,userPswd,username,email,createTime FROM t_admin WHERE loginAcct=?";
        List<Admin> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Admin.class), username);
        System.out.println(list);
        Admin admin = list.get(0);
        System.out.println(admin);
        String userPswd = admin.getUserPswd();
        //2.设置权限信息
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        authorities.add(new SimpleGrantedAuthority("UPDATE"));
        System.out.println(authorities);
        //3.把admin对象和authorities对象封装到UserDetails中
        return new User(username, userPswd, authorities);
    }
}
