package iceriver.mybatis.test;

import com.github.pagehelper.PageHelper;
import iceriver.mybatis.bean.Emp;
import iceriver.mybatis.mapper.EmpMapper;
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
 * @date: 2022/7/27 18:58
 */
public class PageHelperTest {
    /**
     * 语法：limit index, pageSize
     *      index：当前页的起始索引，index = (pageNum - 1) * pageSize
     *      pageSize：每页显示的条数
     *      pageNum：当前页的页码
     */
    @Test
    public void testPageHelperTest(){
        try{
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory build = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = build.openSession(true);
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
            PageHelper.startPage(1, 4);
            List<Emp> emps = mapper.selectByExample(null);
            emps.forEach(emp -> System.out.println(emp));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
