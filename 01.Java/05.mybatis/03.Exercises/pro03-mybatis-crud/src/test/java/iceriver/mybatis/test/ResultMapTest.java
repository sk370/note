package iceriver.mybatis.test;

import iceriver.mybatis.mapper.DeptMapper;
import iceriver.mybatis.mapper.DynamicSQLMapper;
import iceriver.mybatis.mapper.EmpMapper;
import iceriver.mybatis.pojo.Dept;
import iceriver.mybatis.pojo.Emp;
import iceriver.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/27 8:10
 */
public class ResultMapTest {
    @Test
    public void getAllEmp(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> allEmp = mapper.getAllEmp();
        allEmp.forEach(emp -> System.out.println(emp));
    }
    @Test
    public void getEmpAndDept(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp1 = mapper.getEmpAndDept(2);
        System.out.println(emp1);
    }
    @Test
    public void getEmpAndDeptByStepOne(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp1 = mapper.getEmpAndDeptByStepOne(2);
        System.out.println(emp1.getEmpName());
    }
    @Test
    public void getDeptAndEmp(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = mapper.getDeptAndEmp(2);
        System.out.println(dept);
    }
    @Test
    public void getDeptAndEmpByStepOne(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = mapper.getDeptAndEmpByStepOne(2);
        System.out.println(dept);
    }
    @Test
    public void getEmpByCondition(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        List<Emp> emps = mapper.getEmpByCondition(new Emp(null, "张大佛爷", null, "男", null));
        System.out.println(emps);
    }
    @Test
    public void getEmpByChoose(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        List<Emp> emps = mapper.getEmpByChoose(new Emp(null, "", null, "", null));
        System.out.println(emps);
    }
    @Test
    public void deleteMoreByArray(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        int i = mapper.deleteMoreByArray(new Integer[]{6, 7, 8});
        System.out.println(i);
    }
    @Test
    public void insertMoreByList(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        Emp emp1 = new Emp(null, "a1", 23, "男", "123@qq.com");
        Emp emp2 = new Emp(null, "a2", 23, "男", "123@qq.com");
        Emp emp3 = new Emp(null, "a3", 23, "男", "123@qq.com");
        Emp emp4 = new Emp(null, "a4", 23, "男", "123@qq.com");
        List<Emp> emps = Arrays.asList(emp1, emp2, emp3,emp4);
        int i = mapper.insertMoreByList(emps);
        System.out.println(i);
    }
}
