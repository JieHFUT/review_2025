package com.jiehfut.fssmwebmvcthymeleaf.controller;


import com.jiehfut.fssmwebmvcthymeleaf.bean.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.List;


/**
 * 进行页面跳转
 * 1.前后端不分离
 * 2.后端查询数据，使用 Thymeleaf 将数据渲染到页面模板上
 * 3.后端响应给浏览器整个页面
 * 4.导入 spring-boot-starter-thymeleaf
 * 5.默认页面 src/main/resources/templates
 *   静态资源 src/main/resources/static
 *
 */
@Controller
public class PageController {


    /**
     * 处理登陆请求，访问根目录，跳转到 login 页面
     * @return
     */
    @RequestMapping("/")
    public String index() {
        System.out.println("跳转到登陆页面...");
        return "login"; // 返回页面名称：视图
    }



    @RequestMapping(value = "/login.do",
                    method = RequestMethod.POST)
    public String login(String username,
                        String password,
                        Model model) { // 共享数据，渲染页面数据
        System.out.println("username = " + username + "; password = " + password);
        // 正常向 model 中放置从数据库查询的数据
        model.addAttribute("username", username);
        model.addAttribute("age", 18);

        List<Person> list = Arrays.asList(
                new Person(1, "zhansgan", 18),
                new Person(2, "lisi", 22),
                new Person(3, "wangwu", 23),
                new Person(4, "zhaoliu", 28)
        );

        model.addAttribute("list", list);
        return "success";
    }


}
