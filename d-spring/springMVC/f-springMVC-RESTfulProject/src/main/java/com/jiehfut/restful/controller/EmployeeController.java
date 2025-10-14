package com.jiehfut.restful.controller;


import com.jiehfut.restful.bean.Employee;
import com.jiehfut.restful.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    /**
     增删改查
     getAllEmployee - get - 查询
     getEmployeeById - get - 查询
     deleteEmployee - delete - 删除
     addEmployee - post - 添加
     updateEmployee - put - 更新
     */

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String getAllEmployee(Model model) {
        Collection<Employee> employeeList = employeeDao.getAll();
        // 在域对象中将数据从响应中传递到前端
        model.addAttribute("employeeList", employeeList);
        return "employee_list";
    }


    @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable("id") Integer id) {
        // 从员工列表中删除根据ID查询到的员工
        employeeDao.delete(id);
        // 删除信息以后对前端进行回显操作（重定向）到列表页
        return "redirect:/employee";
    }


    // 前端通过 form 表单的 post 方法向后端添加数据，使用实体类 Employee 接收
    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public String addEmployee(Employee employee) {
        employeeDao.save(employee);
        // 添加信息以后对前端进行回显操作（重定向）到列表页
        return "redirect:/employee";
    }


    // 前端查询某个员工信息，回显到前端，使用  add  "employee/2"
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public String getEmployeeById(@PathVariable("id") Integer id, Model model) {
        // 查询出来的数据通过请求域共享到前端
        Employee employee = employeeDao.get(id);
        model.addAttribute("employee", employee);
        System.out.println(employee.toString());
        return "employee_update";
    }


    // 实现用户的修改功能，前端使用 "/employee" 路由的 put 请求，传递 Employee 对象
    @RequestMapping(value = "/employee", method = RequestMethod.PUT)
    public String updateEmployee(Employee employee) {
        // 保存修改数据
        employeeDao.save(employee);
        return "redirect:/employee";
    }

    
}
