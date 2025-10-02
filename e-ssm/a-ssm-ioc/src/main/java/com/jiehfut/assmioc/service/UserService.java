package com.jiehfut.assmioc.service;


import com.jiehfut.assmioc.bean.Person;
import com.jiehfut.assmioc.bean.Tiger;
import com.jiehfut.assmioc.dao.UserDao;
import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Data
@Service
public class UserService {


    // 类型匹配多个，名字不匹配，使用 @Qualifier 来精确指定匹配哪一个组件名字
    @Qualifier("zhangsan")
    @Autowired
    Person youNeverKonw;


    // Consider marking one of the beans as @Primary, updating the consumer to accept multiple beans,
    // or using @Qualifier to identify the bean that should be consumed
    @Autowired // 类型匹配多个，名字不匹配，或者使用 @Primary 标注一个该类为主组件，
               // 但是一旦声明主组件后，再想根据组件名字匹配就必需使用 @Qualifier("****") 注解，属性名就不能匹配组件了
    Person youNeverKonwToo;


    // @Autowired 支持某类型组件为空 @Autowired(required = false)的情况，没有该类型组件就为 null
    @Autowired(required = false)
    Tiger tiger;

    @Resource // 该注解也可以进行依赖注入
    UserDao userDao;

    /**
     * @Autowired
     * @Resource
     * 这两种注入方式的区别
     * @Autowired 是 package org.springframework.beans.factory.annotation; 为 spring 规范的接口
     * @Resource 是 package jakarta.annotation; 为 java 规范的接口
     * 面向接口规范，Resource 具有更强的通用性，如果更改框架，Resource 通用，但是 Autowired 不通用
     *
     * @Autowired 支持某类型组件为空 @Autowired(required = false)的情况，没有该类型组件就为 null
     * @Resource 不支持
     */



}
