package com.jiehfut.test;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUser {

    @Test
    public void testUserAnnotation() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-annotation.xml");
        User user = (User) context.getBean("user", User.class);
        System.out.println(user);

        /**
         - Creating shared instance of singleton bean 'user'
         - com.jiehfut.test.User@64d43929
         */
    }
}
