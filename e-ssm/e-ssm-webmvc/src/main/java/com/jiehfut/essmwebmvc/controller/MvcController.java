package com.jiehfut.essmwebmvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController// 声明控制器
public class MvcController {

    @ResponseBody // 将返回值作为响应体中
    @RequestMapping("/mvc")
    public String mvc() {
        System.out.println("first mvc...");
        return "first mvc...";
    }

    /**
     * @RequestMapping
     * 路径映射
     *    * : 匹配任意多个字符（0-n个）
     *    ** : 匹配任意多层路径，只能存在最后
     *    ? : 匹配任意单个字符（1个）
     * 如果通配符存在的时候，域某一个精确请求产生矛盾 => 精确优先（访问"/jiehfut" =>  "/jiehfut" > "/jiehfu?" > "/jiehfu*" > ** ）
     *
     * 路径规则：https://docs.spring.io/spring-framework/docs/6.1.11/javadoc-api/org/springframework/web/util/pattern/PathPattern.html
     */
    @ResponseBody
    @RequestMapping("/jiehfut*/ant?/test/**")
    // http://localhost:8080/jiehfut82561893569/antv/test/1/2/3/4/5/6
    public String jiehfut() {
        return "jiehfut";
    }


}
