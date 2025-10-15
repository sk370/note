package iceriver.mybatis.test;

import iceriver.mybatis.mapper.UserMapper;
import iceriver.mybatis.pojo.User;
import iceriver.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/26 7:45
 */
public class UserMapperTest {
    @Test
    public void testInsertUser() throws IOException {
    }
    @Test
    public void testUpdateUser() throws IOException {
    }
    @Test
    public void testDeleteUser() throws IOException {
    }
    @Test
    public void testGetUserById() throws IOException {
    }
    @Test
    public void testGetAllUser(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.getAllUser();
        userList.forEach(user -> System.out.println(user));
    }
    @Test
    public void testGetUserByUsername(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.getUserByUsername("guest");
        System.out.println(user);
    }
    @Test
    public void testCheckLogin(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.checkLogin("guest", "123456");
        System.out.println(user);
    }
    @Test
    public void checkLoginByMap(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("username", "guest");
        map.put("password", "123456");
        User user = userMapper.checkLoginByMap(map);
        System.out.println(user);
    }
    @Test
    public void checkLoginByUser(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User(9,"guest", "123456", 24, "男", "1412409014@qq.com");
        User user2 = userMapper.checkLoginByUser(user);
        System.out.println(user2);
    }
    @Test
    public void checkLoginByParams(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.checkLoginByParams("guest", "123456");
        System.out.println(user);
    }
}
