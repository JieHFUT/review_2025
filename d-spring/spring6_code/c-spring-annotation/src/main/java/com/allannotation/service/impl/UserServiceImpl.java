package com.allannotation.service.impl;



import com.allannotation.service.UserService;
import com.allannotation.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void insertUser() {
        System.out.println("service...");
        userDao.insertUser();
    }
}
