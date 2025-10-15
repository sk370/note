package iceriver.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import iceriver.mybatisplus.bean.User;
import iceriver.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/8/5 10:16
 */
@SpringBootTest
public class ConditionTests {
    @Autowired
    private UserMapper userMapper;
    /**
     * 1. 利用mybatis-plus提供的工具类
     */
    @Test
    public void test01() {
//        SELECT id,name,age,email FROM user WHERE (age >= ? AND age <= ?)
        String name = "";
        Integer ageBegin = 20;
        Integer ageEnd = 30;

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            //isNotBlank判断某个字符创是否不为空字符串、不为null、不为空白符
            queryWrapper.like("user_name", name);
        }
        if (ageBegin != null) {
            queryWrapper.ge("age", ageBegin);
        }
        if (ageEnd != null) {
            queryWrapper.le("age", ageEnd);
        }
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }
    /**
     * 2. 使用带condition参数的重载方法构建查询条件
     */
    @Test
    public void test02() {
//        SELECT id,name,age,email FROM user WHERE (age >= ? AND age <= ?)
        String name = "";
        Integer ageBegin = 20;
        Integer ageEnd = 30;

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(name), "user_name", name)
                .ge(ageBegin != null, "age", ageBegin)
                .le(ageEnd != null, "age", ageEnd);
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }
}
