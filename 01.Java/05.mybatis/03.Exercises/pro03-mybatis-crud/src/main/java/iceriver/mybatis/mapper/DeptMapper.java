package iceriver.mybatis.mapper;

import iceriver.mybatis.pojo.Dept;
import iceriver.mybatis.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/27 8:05
 */
public interface DeptMapper {
    /**
     * 多对一查询：查询指定员工的信息及所在部门，分步二：根据did查询部门
     * @param did
     * @return
     */
    Dept getEmpAndDeptByStepTwo(@Param("did") Integer did);

    /**
     * 根据部门编号查询部门信息
     * @param did
     * @return
     */
    Dept getDeptAndEmp(@Param("did") Integer did);

    /**
     * 一对多查询：查询指定部门的信息及所有员工，分步一：根据did查询部门
     * @param did
     * @return
     */
    Dept getDeptAndEmpByStepOne(@Param("did") Integer did);
}
