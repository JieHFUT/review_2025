package com.jiehfut.auto.controller;

import com.jiehfut.auto.service.UserService;

public class UserController {

    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    // 配置通过 set 给 userService 赋值
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /* 原生的调用关系是要实现 service 中的类，然后调用其中的方法。在 spring 中使用自动装配来实现 */
    public void addUser() {
        // 调用 service 方法
        userService.addUserService();
        System.out.println("userController 中的方法执行了...");
    }
    
}