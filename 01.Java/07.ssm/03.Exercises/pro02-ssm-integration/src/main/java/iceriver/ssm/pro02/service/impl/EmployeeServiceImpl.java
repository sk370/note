package iceriver.ssm.pro02.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import iceriver.ssm.pro02.mapper.EmployeeMapper;
import iceriver.ssm.pro02.pojo.Employee;
import iceriver.ssm.pro02.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/8/1 17:20
 */
@Transactional//开启事务
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeMapper.getAllEmployees();
    }

    @Override
    public PageInfo<Employee> getEmployeePage(Integer pageNum) {
        //开启分页功能
        PageHelper.startPage(pageNum, 8);
        List<Employee> list = employeeMapper.getAllEmployees();
        PageInfo<Employee> page = new PageInfo<>(list,5);
        return page;
    }
}
