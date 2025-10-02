package com.jiehfut.autowired.controller;


import com.jiehfut.autowired.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller// 使用注解向容器中注入组件的时候，默认的组件名称就是类名的首字母小写
public class UserController {
/*
    @Autowired 根据类型进行匹配
    @Qualifier 根据名称进行匹配

    // 1.通过属性注入 service
    @Autowired // 根据类型找到对应的实现类的对象，完成注入
    private UserService userService;

    // 2.通过 setter 方法注入
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    // 3.通过构造方法中注入
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 4.形参上注入
    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    // 5.只有一个有参构造，没有第二个构造器的时候可以将 @Autowired 省略
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
*/

    // 6. @Autowired 注解和 Qualifier 注解联合



    @Autowired
    private UserService userService;


    public void insertUser(){
        System.out.println("controller...");
        userService.insertUser();
    }



}