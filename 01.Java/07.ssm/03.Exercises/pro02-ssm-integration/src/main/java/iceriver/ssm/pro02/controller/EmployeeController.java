package iceriver.ssm.pro02.controller;

import com.github.pagehelper.PageInfo;
import iceriver.ssm.pro02.pojo.Employee;
import iceriver.ssm.pro02.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/8/1 17:12
 */
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/")
    public String index(){
        int i = 3;
        return "index";
    }

    @RequestMapping(value = "/employee/page/{pageNum}",method = RequestMethod.GET)
    public String getEmployeePage(@PathVariable("pageNum") Integer pageNum, Model model){
        PageInfo<Employee> page = employeeService.getEmployeePage(pageNum);
        model.addAttribute("page",page);
        return "employee_list2";
    }

    @RequestMapping(value = "/employee",method = RequestMethod.GET)
    public String getAllEmployees(Model model){
        List<Employee> list = employeeService.getAllEmployees();
        model.addAttribute("list", list);
        return "employee_list";
    }

}
