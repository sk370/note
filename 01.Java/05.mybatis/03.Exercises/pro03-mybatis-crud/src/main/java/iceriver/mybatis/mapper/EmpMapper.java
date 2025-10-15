package iceriver.mybatis.mapper;

import iceriver.mybatis.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/27 8:04
 */
public interface EmpMapper {
    /**
     * 查看所有员工的详细信息
     * @return
     */
    List<Emp> getAllEmp();

    /**
     * 查看指定员工的信息及所在部门
     * @param eid
     * @return
     */
    Emp getEmpAndDept(@Param("eid") Integer eid);

    /**
     * 多对一查询：查询指定员工的信息及所在部门，分步一：查询所在部门did
     * @param eid
     * @return
     */
    Emp getEmpAndDeptByStepOne(@Param("eid") Integer eid);

    /**
     * 一对多查询：查询指定部门的信息及所有员工，分步二：根据did查询员工
     * @param did
     * @return
     */
    List<Emp> getDeptAndEmpByStepTwo(@Param("did") Integer did);
}
