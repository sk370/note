package iceriver.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import iceriver.mybatisplus.bean.User;
import iceriver.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/8/4 21:09
 */
@SpringBootTest
public class UserTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList(){
        List<User> users = userMapper.selectList(null);
        users.forEach(user -> System.out.print(user));
    }
    @Test
    public void testSelectMapById(){
        Map<String, Object> map = userMapper.selectMapById(1L);
        System.out.println(map);
    }

    @Test
    public void testPageVo(){
        Page<User> page = new Page<>(1,3);
        Page<User> page1 = userMapper.selectPageVo(page, 20);
        System.out.println(page);
        System.out.println(page1);
        System.out.println(page==page1);
    }
}
