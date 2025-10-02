package com.jiehfut.gssmwebmvcrestful;

import com.jiehfut.gssmwebmvcrestful.bean.Employee;
import com.jiehfut.gssmwebmvcrestful.dao.EmployeeDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GSsmWebmvcResTfulApplicationTests {

    @Autowired
    EmployeeDao employeeDao;

    @Test
    void contextLoads() {
        Employee employeeById = employeeDao.getEmployeeById(4L);
        System.out.println("employee = " + employeeById);
    }

}
