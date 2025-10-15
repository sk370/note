package iceriver.mybatis.test;

import iceriver.mybatis.bean.Emp;
import iceriver.mybatis.bean.EmpExample;
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
 * @date: 2022/7/27 18:25
 */
public class MBGTest {
    @Test
    public void testMBG(){
        try{
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory build = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = build.openSession(true);
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
            //1. 查询所有员工
//            List<Emp> emps = mapper.selectByExample(null);
//            emps.forEach(emp -> System.out.println(emp));

            //2. 根据条件查询
//            EmpExample empExample = new EmpExample();
//            empExample.createCriteria().andEmpNameEqualTo("a1").andAgeGreaterThan(20);
//            empExample.or().andDidIsNotNull();
//            List<Emp> emps = mapper.selectByExample(empExample);
//            emps.forEach(emp -> System.out.println(emp));

            //3. 修改
            mapper.updateByPrimaryKey(new Emp(1, "wuxie", null, null, null,null));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
