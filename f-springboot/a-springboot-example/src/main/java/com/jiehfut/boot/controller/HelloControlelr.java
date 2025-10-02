package com.jiehfut.boot.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class HelloControlelr {

    @GetMapping("/hello")
    public String hello() {
        return "hello,springboot 3";
    }

    


}
