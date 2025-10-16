package com.jiehfut.assmioc.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
@Component
@AllArgsConstructor
public class Chicken {

    /**
     * 使用 @Value 注解给属性默认值
     * 1. @Value("字面值")：直接赋值
     * 2. @Value("${}")：动态从配置文件中取出某一项的值
     * 3. @Value("#{SpEL}")，SPRING EXPRESSION LANGUAGE：spring 表达式语言
     *
     */
    @Value("胖大鸡")
    private String name;

    @Value("${chicken.age:2}") // :是如果没有取到这个值，使用 : 后面的默认值
    private int age;

    @Value("#{10*26}")
    private int high;

    @Value("#{T(java.util.UUID).randomUUID().toString()}")
    private String id;

    @Value("#{'hello world'.substring(0, 5)}")
    private String message;

    @Value("#{1 > 2 ? 'true ':'false '}")
    private boolean flag;

    @Value("#{new String('you never know')}")
    private String method;


    public Chicken() {
        String s = UUID.randomUUID().toString();
        System.out.println("uuid = " + s);
    }



}
