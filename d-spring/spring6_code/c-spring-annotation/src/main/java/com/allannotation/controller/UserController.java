package com.allannotation.controller;


import com.allannotation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {


    @Autowired
    private UserService userService;

    public void insertUser(){
        System.out.println("controller...");
        userService.insertUser();
    }

}