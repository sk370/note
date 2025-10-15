package iceriver.mybatisplus;

import iceriver.mybatisplus.bean.User;
import iceriver.mybatisplus.enums.SexEnum;
import iceriver.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/8/5 17:21
 */
@SpringBootTest
public class EnumTests {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void test(){
        User user = new User();
        user.setName("admin");
        user.setAge(33);
        user.setSex(SexEnum.MALE);
        int insert = userMapper.insert(user);
        System.out.println(insert);
    }
}
