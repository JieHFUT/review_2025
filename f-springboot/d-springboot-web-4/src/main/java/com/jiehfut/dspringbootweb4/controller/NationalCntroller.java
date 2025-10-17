package com.jiehfut.dspringbootweb4.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@Controller
public class NationalCntroller {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @Autowired
    MessageSource messageSource; // 国际化用于获取配置中的信息的组件

    @GetMapping("/messagesource")
    public void messageSource(HttpServletRequest req, HttpServletResponse res) {
        // 获取国际化请求后某一个 key 对应的 value 值
        Locale locale = req.getLocale();
        messageSource.getMessage("login", null, locale);
    }

}
