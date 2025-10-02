package com.jiehfut.selfioc.service.impl;

import com.jiehfut.selfioc.annotation.Bean;
import com.jiehfut.selfioc.annotation.Di;
import com.jiehfut.selfioc.dao.UserDao;
import com.jiehfut.selfioc.service.UserService;

@Bean
public class UserServiceImpl implements UserService {

    @Di
    private UserDao userDao;

    @Override
    public void add() {
        System.out.println("userServiceImpl add...");
        userDao.add();
    }
}
