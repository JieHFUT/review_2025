package com.jiehfut.assmioc.controller;



import com.jiehfut.assmioc.bean.Person;
import com.jiehfut.assmioc.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Data
@Controller  // 将该控制器组件放在容器中
public class UserController{

    /**
     * 自动装配的流程
     * 1.先按照类型 UserService 去寻找这个组件
     *     如果有且只有一个该类型的组件，直接注入
     *     如果该类型有多个组件，再按照名字 userService 去寻找
     *         如果找不到该类型下的该名字的组件：报错
     *         如果找到了，直接注入该类型的该名字的组件
     *
     *
     */
    @Autowired // 自动注入组件（依赖注入/装配）=> 原理就是 spring 调用容器的 getBean
    UserService userService;

    @Autowired
    Person person;

    @Autowired
    Person zhangsan;

    @Autowired // 拿到所有的该类型的组件
    List<Person> personList;

    @Autowired // 拿到所有的该类型的组件，组件名称作为 key
    Map<String, Person> personMap;

    @Autowired // 拿到 ioc 容器组件
    ApplicationContext applicationContext;


}
