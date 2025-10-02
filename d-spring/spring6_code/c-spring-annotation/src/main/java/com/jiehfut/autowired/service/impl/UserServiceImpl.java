package com.jiehfut.autowired.service.impl;


import com.jiehfut.autowired.dao.UserDao;
import com.jiehfut.autowired.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    // 6. @Autowired 注解和 @Qualifier 注解联合
    @Autowired
    @Qualifier(value = "userDaoImplX") // 指定注入的组件名称
    private UserDao userDao;



    @Override
    public void insertUser() {
        System.out.println("service...");
        userDao.insertUser();
    }

}
