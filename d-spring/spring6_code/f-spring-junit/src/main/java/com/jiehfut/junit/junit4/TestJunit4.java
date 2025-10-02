package com.jiehfut.junit.junit4;


import com.jiehfut.junit.junit5.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Component
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bean.xml")
public class TestJunit4 {

    @Autowired
    private User user;

    @Test
    public void testJunit4(){
        System.out.println("user = " + user);
        user.run();
    }
}
