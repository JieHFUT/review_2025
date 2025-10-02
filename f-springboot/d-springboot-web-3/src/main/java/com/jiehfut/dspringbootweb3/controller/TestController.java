package com.jiehfut.dspringbootweb3.controller;


import com.jiehfut.dspringbootweb3.bean.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class TestController {


    @RequestMapping("/")
    public String index() {

        return "index";
    }



    @GetMapping("/test-page")
    public String testPage(@RequestParam("name") String username,
                           Model model){

        // 生成模板的逻辑视图地址
        // 物理视图：前缀 + 逻辑 + 后缀
        //         classpath:/templates/page-test/html

        // 把需要共享到页面上的数据共享到 model 中，就可以通过视图渲染到页面上
        model.addAttribute("username", username);
        return "page-test";
    }


    @RequestMapping("/list")
    public String list(Model model){
        List<Person> personList = Arrays.asList(
                new Person(1, "zhangsan", "123456",19, "354u123@163.com", "male", "主管"),
                new Person(1, "lisi", "123456", 23, "354u123@163.com", "male", "部长"),
                new Person(1, "wangwu", "123456", 13, "", "female", "主任"),
                new Person(1, "zhaoliu", "123456", 22, "354u135213@163.com", "male", "部长"),
                new Person(1, "wuqi", "123456", 16, "2154354u123@163.com", "female", "主管")
        );
        model.addAttribute("persons", personList);
        return "list";
    }

}
