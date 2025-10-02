package com.jiehfut.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("/")
    public String index() {
        // 返回视图名称
        return "index";
    }


    @RequestMapping("/param")
    public String param() {
        return "test-param";
    }

}
