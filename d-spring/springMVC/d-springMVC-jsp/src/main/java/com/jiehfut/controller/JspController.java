package com.jiehfut.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JspController {

    @RequestMapping("/success")
    public String success() {
        // 被视图解析器解析，加上前缀 && 后缀
        // 转化视图（internalResourceView）
        return "success";
    }






}
