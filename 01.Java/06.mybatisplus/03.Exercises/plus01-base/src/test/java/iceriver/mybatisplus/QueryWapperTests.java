package iceriver.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import iceriver.mybatisplus.bean.User;
import iceriver.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/8/5 9:46
 */
@SpringBootTest
public class QueryWapperTests {

    @Autowired
    private UserMapper userMapper;
    /**
     * 1. 组装查询条件
     */
    @Test
    public void test01(){
//        查询用户名包含a，年龄在20到30之间的所有用户信息
//        SELECT id,name,age,email FROM user WHERE (name LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "a")
                .between("age",20,30)
                .isNotNull("email");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(user -> System.out.print(user));
    }
    /**
     * 2. 组装排序条件
     */
    @Test
    public void test02(){
//        按年龄降序查询用户，如果年龄相同则按id升序排列
//        SELECT id,name,age,email FROM user ORDER BY age DESC,id ASC
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age")
                .orderByAsc("id");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(user -> System.out.print(user));
    }
    /**
     * 3. 组装删除功能
     */
    @Test
    public void test03(){
//        删除邮箱地址为null的用户
//        DELETE FROM user WHERE (email IS NULL)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        int result = userMapper.delete(queryWrapper);
        System.out.println("result" + result);
    }
    /**
     * 4. 组装修改功能
     */
    @Test
    public void test04(){
//        将（年龄大于20并且用户名包含a）或者邮箱为null的用户信息修改
//        UPDATE user SET name=?, email=? WHERE (age > ? AND name LIKE ? OR email IS NULL)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 20)
                .like("name", "a")
                .or()
                .isNull("email");
        User user1 = new User();
        user1.setName("xiaoming");
        user1.setEmail("123@qq.com");
        int result = userMapper.update(user1, queryWrapper);
        System.out.println("result" + result);
    }

    /**
     * 5. 组装查询优先级
     */
    @Test
    public void test05(){
//        将用户名包含a并且（年龄大于20或者邮箱为null）的用户信息修改
//        mybatis-plus的条件构造器中lambda中的条件优先执行
//        UPDATE user SET name=?, email=? WHERE (name LIKE ? AND (age > ? AND email IS NULL))
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "a")
                .and(i -> i.gt("age", 20).isNull("email"));
        User user1 = new User();
        user1.setName("dazhuang");
        user1.setEmail("312@qq.com");
        int result = userMapper.update(user1, queryWrapper);
        System.out.println("result" + result);
    }

    /**
     * 6. 组装查询指定列
     */
    @Test
    public void test06(){
//        查询用户名、年龄、邮箱
//        SELECT name,age,email FROM user
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name", "age", "email");
        List<Map<String, Object>> users = userMapper.selectMaps(queryWrapper);
        users.forEach(user -> System.out.println(user));
    }
    /**
     * 7. 组装子查询
     */
    @Test
    public void test07(){
//        查询id小于等于100的用户
//        SELECT id,name,age,email FROM user WHERE (id IN (select id from user where id<=100))
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("id", "select id from user where id<=100");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(user -> System.out.println(user));
    }
}
