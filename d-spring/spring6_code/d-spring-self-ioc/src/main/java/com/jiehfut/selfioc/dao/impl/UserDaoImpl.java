package com.jiehfut.selfioc.dao.impl;

import com.jiehfut.selfioc.annotation.Bean;
import com.jiehfut.selfioc.dao.UserDao;


@Bean
public class UserDaoImpl implements UserDao {


    @Override
    public void add() {
        System.out.println("userDaoImpl add...");
    }
}
