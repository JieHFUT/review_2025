package com.jiehfut.autowired;

import com.jiehfut.autowired.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAutowired {
    @Test
    public void testAutowired(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-annotation.xml");
        UserController userController = context.getBean(UserController.class);
        userController.insertUser();

        /**
         controller...
         service...
         userdao...

         */
    }
}
