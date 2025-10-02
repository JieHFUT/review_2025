package com.allannotation;

import com.allannotation.config.SpringConfig;
import com.allannotation.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestConfig {
    // 通过加载配置类进行操作
    @Test
    public void testConfig(){
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        UserController userController = context.getBean(UserController.class);
        userController.insertUser();
    }
}
