package iceriver.mybatis.test;

import iceriver.mybatis.mapper.UserMapper;
import iceriver.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/26 7:45
 */
public class UserMapperTest {
    @Test
    public void testInsertUser() throws IOException {
        // 1. 加载核心配置文件
        InputStream istream = Resources.getResourceAsStream("mybatis-config.xml");
        // 2. 获取SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 3. 获取SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(istream);
        // 4. 获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 5. 获取mapper接口（实现类的）对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);//底层使用了代理模式创建实现类
        // 6. 调用mapper接口方法，执行测试
        int result = userMapper.insertUser();
        // 7. 提交事务
        sqlSession.commit();//由于核心配置文件开启了事务：<transactionManager type="JDBC"/>，所以这里需要提交
        System.out.println(result);
    }
    @Test
    public void testUpdateUser() throws IOException {
        // 1. 加载核心配置文件
        InputStream istream = Resources.getResourceAsStream("mybatis-config.xml");
        // 2. 获取SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 3. 获取SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(istream);
        // 4. 获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 5. 获取mapper接口（实现类的）对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);//底层使用了代理模式创建实现类
        // 6. 调用mapper接口方法，执行测试
        userMapper.updateUser();
        // 7. 提交事务
        sqlSession.commit();//由于核心配置文件开启了事务：<transactionManager type="JDBC"/>，所以这里需要提交
    }
    @Test
    public void testDeleteUser() throws IOException {
        // 1. 加载核心配置文件
        InputStream istream = Resources.getResourceAsStream("mybatis-config.xml");
        // 2. 获取SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 3. 获取SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(istream);
        // 4. 获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 5. 获取mapper接口（实现类的）对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);//底层使用了代理模式创建实现类
        // 6. 调用mapper接口方法，执行测试
        userMapper.deleteUser();
        // 7. 提交事务
        sqlSession.commit();//由于核心配置文件开启了事务：<transactionManager type="JDBC"/>，所以这里需要提交
    }
    @Test
    public void testGetUserById() throws IOException {
        // 1. 加载核心配置文件
        InputStream istream = Resources.getResourceAsStream("mybatis-config.xml");
        // 2. 获取SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 3. 获取SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(istream);
        // 4. 获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 5. 获取mapper接口（实现类的）对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);//底层使用了代理模式创建实现类
        // 6. 调用mapper接口方法，执行测试
        User user = userMapper.getUserById();
        // 7. 提交事务
        sqlSession.commit();//由于核心配置文件开启了事务：<transactionManager type="JDBC"/>，所以这里需要提交
        System.out.println(user);
    }
    @Test
    public void testGetAllUser() throws IOException {
        // 1. 加载核心配置文件
        InputStream istream = Resources.getResourceAsStream("mybatis-config.xml");
        // 2. 获取SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 3. 获取SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(istream);
        // 4. 获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 5. 获取mapper接口（实现类的）对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);//底层使用了代理模式创建实现类
        // 6. 调用mapper接口方法，执行测试
        List<User> userList = userMapper.getAllUser();
        // 7. 提交事务
        sqlSession.commit();//由于核心配置文件开启了事务：<transactionManager type="JDBC"/>，所以这里需要提交
        userList.forEach(user -> System.out.println(user));
    }
}
