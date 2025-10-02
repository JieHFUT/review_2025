package com.jiehfut.domain.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {


//    @RequestMapping("/")
//    public String index() {
//        return "index";
//    }


    @RequestMapping("/test-view")
    public String view() {
        return "test-view";
    }







}
