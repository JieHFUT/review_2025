package com.allannotation.dao.impl;

import com.allannotation.dao.UserDao;
import org.springframework.stereotype.Repository;


@Repository
public class UserDaoImpl implements UserDao {
    @Override
    public void insertUser() {
        System.out.println("userdao...");
    }
}
