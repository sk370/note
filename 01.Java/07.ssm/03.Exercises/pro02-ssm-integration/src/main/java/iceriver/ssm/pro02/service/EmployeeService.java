package iceriver.ssm.pro02.service;

import com.github.pagehelper.PageInfo;
import iceriver.ssm.pro02.pojo.Employee;

import java.util.List;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/8/1 17:20
 */
public interface EmployeeService {
    /**
     * 查询所有员工信息
     * @return
     */
    List<Employee> getAllEmployees();

    /**
     * 分页查询员工信息
     * @param pageNum
     * @return
     */
    PageInfo<Employee> getEmployeePage(Integer pageNum);
}
