package com.jiehfut.resource;

import com.jiehfut.resource.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSource {
    @Test
    public void testSource(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-annotation.xml");
        UserController userController = context.getBean(UserController.class);
        userController.insertUser();
        /**
         * controller...
         * service...
         * userdao-x...
         */
    }
}
