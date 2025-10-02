package com.jiehfut.auto.service.impl;

import com.jiehfut.auto.dao.UserDao;
import com.jiehfut.auto.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    @Override
    public void addUserService() {
        // 调用 dao 层
        userDao.addUserDao();
        System.out.println("userService 中的方法执行了...");
    }
}
