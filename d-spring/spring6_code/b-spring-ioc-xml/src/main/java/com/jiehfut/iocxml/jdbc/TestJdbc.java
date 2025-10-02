package com.jiehfut.iocxml.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestJdbc {
    @Test
    public void testJdbc(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/atchery?serverTimezone=UTC");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("959452");
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        // 以上对数据库的设置就完成了
    }
    
    
    @Test
    public void testDI(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-jdbc.xml");
        DruidDataSource druidDataSource = context.getBean(DruidDataSource.class);
        System.out.println(druidDataSource.getUrl());
        /**
         * jdbc:mysql://localhost:3306/atchery?serverTimezone=UTC
         */
    }
}
