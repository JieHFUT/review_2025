package com.jiehfut.hssmwebmvcpractice.service.impl;


import com.jiehfut.hssmwebmvcpractice.bean.Employee;
import com.jiehfut.hssmwebmvcpractice.dao.EmployeeDao;
import com.jiehfut.hssmwebmvcpractice.exception.BizException;
import com.jiehfut.hssmwebmvcpractice.exception.BizExceptionEnume;
import com.jiehfut.hssmwebmvcpractice.service.EmployeeService;
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
        if (employee.getId() == null) {
            /**
             * 中断业务的时候，需要让上层知道中断原因
             * 前端关心异常状态，后端关心业务流程处理
             * 推荐：后端只要编写正确的业务逻辑，如果出现业务逻辑，后端通过抛出异常的方式提前中断业务逻辑，让前端感知异常
             *
             */
            throw new BizException(BizExceptionEnume.EMPLOYEE_ID_NO_EXIST);
        }
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
