package com.jiehfut.resource.controller;


import com.jiehfut.resource.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;

@Controller("myUserController")// 声明该组件在容器中的名称，如果不写明，默认名称就是类名首字母小写
public class UserController {

    // 根据名称进行注入
    @Resource(name = "myUserService") // 1.指定注入的组件名称，如果不指定就会以下面的属性名作为组件名称进行寻找
    private UserService userService;

    // 2.如果不为 Resource 指定名称，也没有对属性名称指定对应的组件的名称，就会根据类型注入
    public void insertUser(){
        System.out.println("controller...");
        userService.insertUser();
    }



}