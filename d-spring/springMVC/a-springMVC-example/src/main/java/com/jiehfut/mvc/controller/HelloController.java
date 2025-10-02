package com.jiehfut.mvc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    // "/"   ==>  /WEB-INF/templates/index.html
    // @RequestMapping() 请求映射的注解，将我们当前的请求和控制器方法建立映射关系
    @RequestMapping(value = "/")
    public String index() {
        // 返回视图名称
        return "index";
    }

    @RequestMapping("/target")
    public String toTarget() {
        // 返回视图名称
        return "target";
    }


}
