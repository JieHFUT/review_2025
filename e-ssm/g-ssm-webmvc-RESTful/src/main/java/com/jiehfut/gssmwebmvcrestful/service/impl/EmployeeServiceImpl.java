package com.jiehfut.gssmwebmvcrestful.service.impl;

import com.jiehfut.gssmwebmvcrestful.bean.Employee;
import com.jiehfut.gssmwebmvcrestful.dao.EmployeeDao;
import com.jiehfut.gssmwebmvcrestful.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeDao.getEmployeeById(id);
    }

    @Override
    public int addEmployee(Employee employee) {
        return employeeDao.addEmployee(employee);
    }

    @Override
    public int updateEmployee(Employee employee) {
        // 防空处理
        if (employee.getId() == null) return -1;
        Employee employeeById = getEmployeeById(employee.getId());
        // 逐个覆盖（没有传递的保持原值）
        if (employeeById == null) return -2;
        if (StringUtils.hasText(employee.getName())) { // name 有值，不为 bull，不是空串，不是空白字符
            employeeById.setName(employee.getName());
        }
        if (StringUtils.hasText(employee.getEmail())) {
            employeeById.setEmail(employee.getEmail());
        }
        if (StringUtils.hasText(employee.getAddress())) {
            employeeById.setAddress(employee.getAddress());
        }
        if (StringUtils.hasText(employee.getGender())) {
            employeeById.setGender(employee.getGender());
        }
        if (employee.getAge() != null) {
            employeeById.setAge(employee.getAge());
        }
        if (employee.getSalary() != null) {
            employeeById.setSalary(employee.getSalary());
        }
        return employeeDao.updateEmployee(employeeById);
    }

    @Override
    public int deleteEmployee(Long id) {
        return employeeDao.deleteEmployee(id);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeDao.getAllEmployee();
    }

}
