package iceriver.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import iceriver.mybatisplus.bean.User;
import iceriver.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/8/5 10:24
 */
@SpringBootTest
public class LambdaQueryWrapperTests {
    @Autowired
    private UserMapper userMapper;
    /**
     * 1. LambdaQueryWrapper
     */
    @Test
    public void test01() {
//        SELECT id,name,age,email FROM user WHERE (age >= ? AND age <= ?)
        String name = "";
        Integer ageBegin = 20;
        Integer ageEnd = 30;

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(name), User::getName, name)
                .ge(ageBegin != null, User::getAge, ageBegin)
                .le(ageEnd != null, User::getAge, ageEnd);
        List<User> list = userMapper.selectList(lambdaQueryWrapper);
        list.forEach(System.out::println);
    }
}
