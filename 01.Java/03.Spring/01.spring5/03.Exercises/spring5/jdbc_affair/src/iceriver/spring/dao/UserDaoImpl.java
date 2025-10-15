package iceriver.spring.dao;

import iceriver.spring.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/16 16:35
 */

@Repository
public class UserDaoImpl implements UserDao {
//    注入jdbc
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void addMoney() {
        String sql = "update t_accout set money = money + ? where username = ?";
        jdbcTemplate.update(sql, 100, "marry");
        System.out.println("已转账");
    }

    @Override
    public void reduceMoney() {
        String sql = "update t_accout set money = money - ? where username = ?";
        jdbcTemplate.update(sql, 100, "lucy");
        System.out.println("已到账");
    }
}
