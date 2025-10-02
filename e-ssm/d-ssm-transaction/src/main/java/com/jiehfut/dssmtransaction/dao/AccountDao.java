package com.jiehfut.dssmtransaction.dao;


import com.jiehfut.dssmtransaction.bean.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AccountDao {

    @Autowired
    JdbcTemplate jdbcTemplate;


    /**
     * 按照用户名扣款
     * @param username
     * @param delta
     */
    public void updateByUsername(String username, BigDecimal delta) {
        String sql = "update account set balance = balance - ? where username = ?";
        jdbcTemplate.update(sql, delta, username);
    }





}
