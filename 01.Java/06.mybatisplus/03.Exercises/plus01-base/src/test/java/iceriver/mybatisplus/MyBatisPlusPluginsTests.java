package iceriver.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import iceriver.mybatisplus.bean.User;
import iceriver.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/8/5 11:16
 */
@SpringBootTest
public class MyBatisPlusPluginsTests {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void test(){
        Page<User> page = new Page<>(1, 3);
        Page<User> page1 = userMapper.selectPage(page, null);
        System.out.println(page);
        System.out.println("-------------------");
        System.out.println(page1==page);//true
    }
}
