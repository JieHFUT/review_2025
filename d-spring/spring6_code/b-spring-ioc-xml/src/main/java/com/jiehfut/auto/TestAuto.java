package com.jiehfut.auto;

import com.jiehfut.auto.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAuto {
    
    @Test
    public void testAuto(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-auto.xml");
        
        UserController userController = context.getBean("userController", UserController.class);
        userController.addUser();

        /**
         userDao 中的方法执行了...
         userService 中的方法执行了...
         userController 中的方法执行了...

         需要保证接口的实现类只有一个实现类
         */
    }
}
