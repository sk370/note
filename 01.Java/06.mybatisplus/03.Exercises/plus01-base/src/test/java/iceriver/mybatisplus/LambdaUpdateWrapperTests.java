package iceriver.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import iceriver.mybatisplus.bean.User;
import iceriver.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/8/5 10:38
 */
@SpringBootTest
public class LambdaUpdateWrapperTests {
    @Autowired
    private UserMapper userMapper;

    /**
     * 1. LambdaUpdateWrapper
     */
    @Test
    public void test01(){
//        将用户名包含a并且（年龄大于20或者邮箱为null）的用户信息修改
//        UPDATE user SET name=?,email=? WHERE (name LIKE ? AND (age > ? AND email IS NULL))
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.like(User::getName, "i")
                           .and(i -> i.gt(User::getAge, 20).isNull(User::getEmail));
        lambdaUpdateWrapper.set(User::getName,"大王").set(User::getEmail, "newd@sina.com");
        int result = userMapper.update(null, lambdaUpdateWrapper);
        System.out.println("result" + result);
    }
}
