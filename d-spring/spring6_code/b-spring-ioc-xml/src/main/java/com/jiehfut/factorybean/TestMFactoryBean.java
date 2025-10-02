package com.jiehfut.factorybean;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMFactoryBean {
    @Test
    public void testMFactoryBean(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-factorybean.xml");
        User user = (User) context.getBean("user");

        System.out.println(user);// com.jiehfut.factorybean.User@3bb9efbc
    }
}
