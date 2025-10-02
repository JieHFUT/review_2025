package com.jiehfut.autowired.dao.impl;

import com.jiehfut.autowired.dao.UserDao;
import org.springframework.stereotype.Repository;


@Repository
public class UserDaoImplY implements UserDao {
    @Override
    public void insertUser() {
        System.out.println("userdao-y...");
    }
}
