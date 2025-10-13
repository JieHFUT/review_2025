package com.jiehfut.junit.junit5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


// @ExtendWith(SpringExtension.class)
// @ContextConfiguration("classpath:bean.xml")
// 或者下面一个注解
@Component
@SpringJUnitConfig(locations = "classpath:bean.xml") // 取代了之前 ApplicationContext context = new ClassPathXmlApplicationContext("xxx.xml");
public class TestJunit5 {

    // 直接注入
    @Autowired
    private User user;

    @Test
    public void testJunit5(){
        System.out.println("user = " + user);
        user.run();
    }


}
