package com.jiehfut.jdbc;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

@Component
@SpringJUnitConfig(locations = "classpath:bean-jdbc.xml")
public class JDBCTemplateTest {

    /**
     * 注入相关类
     * [JdbcTemplate] 是 [Spring框架] 提供的一个工具类，用于简化 [JDBC] 编程。
     * 它通过封装 JDBC 操作，减少了样板代码的编写，使得开发者可以更专注于业务逻辑的实现，
     * 而不是处理繁琐的数据库连接和异常处理‌
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testInsert() {
        String sql = "insert into t_emp values (NULL, ?, ?, ?)";
        // (sql, "zhansgan", 18, "男")
        // (sql, "lisi", 14, "男")
        // (sql, "wangwu", 35, "女")
        // (sql, "zhaoliu", 43, "男")
        int rows = jdbcTemplate.update(sql, "zhaoliu", 43, "男");
        System.out.println("rows = " + rows);
    }


    @Test
    public void testUpdate(){
        String sql = "update t_emp set age = ? where id = ?";
        int rows = jdbcTemplate.update(sql, 999, 1);
        System.out.println("rows = " + rows);
    }


    @Test
    public void testDelete(){
        String sql = "delete from t_emp where id = ?";
        int rows = jdbcTemplate.update(sql, 4);
        System.out.println("rows = " + rows);
    }



    // 查询表中数据，以对象形式返回
    @Test
    public void testQueryReturnObject(){
        String sql = "select * from t_emp where id = ?";
        // queryForObject(String sql, RowMapper<T> rowMapper, @Nullable Object... args)
        Emp queryResult = jdbcTemplate.queryForObject(sql, (resultSet, rowNum) -> {
            Emp emp = new Emp();
            emp.setId(resultSet.getInt("id"));
            emp.setName(resultSet.getString("name"));
            emp.setAge(resultSet.getInt("age"));
            emp.setSex(resultSet.getString("sex"));
            return emp;
        }, 1);
        System.out.println(queryResult.toString());
    }



    @Test
    public void testQueryReturnObjectWithoutResultSet(){
        String sql = "select * from t_emp where id = ?";
        Emp queryResult = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Emp.class), 1);
        System.out.println(queryResult.toString());
    }


    // 查询表中数据，以 list 集合形式返回
    @Test
    public void testQueryReturnList(){
        // public <T> List<T> query(String sql, RowMapper<T> rowMapper)
        String sql = "select * from t_emp";
        List<Emp> queryResult = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Emp.class));
        for (Emp emp : queryResult) {
            System.out.println(emp.toString());
        }
    }



    // 查询表中多少条记录
    @Test
    public void testSingleValue(){
        String sql = "select count(*) from t_emp";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println("count = " + count);
    }

    

}
