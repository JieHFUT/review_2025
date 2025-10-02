package com.jiehfut.issmspringbootexample;

import com.jiehfut.issmspringbootexample.properties.DogProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
class ISsmSpringbootExampleApplicationTests {

    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() {

        System.out.println("更换组件前：datasource = " + dataSource); // 更换组件前：datasource = HikariDataSource (null)
        /**
         * 可见 springboot 在引入来数据库连接的场景后自动导入的组件是 HikariDataSource
         * 如果想要自己更换为 DruidDataSource
         * 1.引入 druid 依赖（带版本）
         * 2.编写一个配置类
         * 3.注入你要注入的组件
         */
        System.out.println("更换组件后：datasource = " + dataSource); // DruidDataSource
    }


    /**
     * 自己设置属性绑定
     */
    @Autowired
    DogProperties dogProperties;
    @Test
    public void testProperties(){
        System.out.println("dog-properties = " + dogProperties);
        System.out.println("name = " + dogProperties.getName());
    }


}
