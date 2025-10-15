package iceriver.mybatis.utils;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/26 11:44
 */
public class SqlSessionUtils {
    /**
     * 获取sqlSession对象
     * @return
     */
    public static SqlSession getSqlSession(){
        SqlSession sqlSession = null;
        try {
            InputStream inputStream = null;
            inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession(true);//开启自动提交事务
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlSession;
    }
}
