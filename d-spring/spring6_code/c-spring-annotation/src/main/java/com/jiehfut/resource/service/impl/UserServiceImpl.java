package com.jiehfut.resource.service.impl;


import com.jiehfut.resource.dao.UserDao;
import com.jiehfut.resource.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


@Service(value = "myUserService")
public class UserServiceImpl implements UserService {


    // 不指定名称，就会根据属性名称（myUserDaox）寻找组件名称进行注入，都不指定的话就会通过类型匹配
    @Resource()
    private UserDao myUserDaox;


    @Override
    public void insertUser() {
        System.out.println("service...");
        myUserDaox.insertUser();
    }
}
