package com.jiehfut.factorybean;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMFactoryBean {

    /**
         FactoryBean是Spring提供的一种整合第三方框架的常用机制。和普通的bean不同，配置一个FactoryBean类型的bean，
         在获取 bean 的时候得到的并不是class属性中配置的这个类的对象，而是getObject()方法的返回值。
         通过这种机制，Spring可以帮我们把复杂组件创建的详细过程和繁琐细节都屏蔽起来，只把最简洁的使用界面展示给我们。
         将来我们整合Mybatis时，Spring 就是通过 FactoryBean 机制来帮我们创建 SqlSessionFactory 对象的。
     */
    @Test
    public void testMFactoryBean(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-factorybean.xml");
        User user = (User) context.getBean("user");

        System.out.println(user);// com.jiehfut.factorybean.User@3bb9efbc
    }
}
