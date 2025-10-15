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
 * @date: 2022/8/5 10:00
 */
@SpringBootTest
public class UpdateWrapperTests {
    @Autowired
    private UserMapper userMapper;

    /**
     * 1. 组装修改条件
     */
    @Test
    public void test01(){
//        将用户名包含a并且（年龄大于20或者邮箱为null）的用户信息修改
//        UPDATE user SET name=?,email=? WHERE (name LIKE ? AND (age > ? AND email IS NULL))
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.like("name", "i")
                     .and(i -> i.gt("age", 20).isNull("email"));
        updateWrapper.set("name","大王").set("email", "newd@sina.com");
        int result = userMapper.update(null, updateWrapper);
        System.out.println("result" + result);
    }
}
