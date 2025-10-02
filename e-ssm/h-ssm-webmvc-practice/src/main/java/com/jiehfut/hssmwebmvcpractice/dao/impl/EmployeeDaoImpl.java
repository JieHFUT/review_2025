package com.jiehfut.hssmwebmvcpractice.dao.impl;


import com.jiehfut.hssmwebmvcpractice.bean.Employee;
import com.jiehfut.hssmwebmvcpractice.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Employee getEmployeeById(Long id) {
        String sql = "select * from employee where id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Employee>(Employee.class), id);
    }

    @Override
    public int addEmployee(Employee employee) {
        String sql = "insert into employee (name, age, email, gender, address, salary, birth) values (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            employee.getName(),
            employee.getAge(),
            employee.getEmail(),
            employee.getGender(),
            employee.getAddress(),
            employee.getSalary(),
            employee.getBirth());
    }

    @Override
    public int updateEmployee(Employee employee) {
        String sql = "update employee set name=?, age=?, email=?, gender=?, address=?, salary=? where id=?";
        return jdbcTemplate.update(sql,
                employee.getName(),
                employee.getAge(),
                employee.getEmail(),
                employee.getGender(),
                employee.getAddress(),
                employee.getSalary());
    }

    @Override
    public int deleteEmployee(Long id) {
        String sql = "delete from employee where id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Employee> getAllEmployee() {
        String sql = "select * from employee";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(Employee.class));
    }

}
