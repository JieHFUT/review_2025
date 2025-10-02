package com.jiehfut.cssmdatasource;

import com.jiehfut.cssmdatasource.bean.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class CSsmDatasourceApplication {

    public static void main(String[] args) {
        // 1.创建 ioc 容器
        ConfigurableApplicationContext ioc = SpringApplication.run(CSsmDatasourceApplication.class, args);
        // 2.获取注册的组件
        Person zhangsan = (Person) ioc.getBean(Person.class);
        System.out.println("zhangsan = " + zhangsan);
    }



    public static void mainOriginal(String[] args) {
        // 通过配置信息获取 bean 组件
        ClassPathXmlApplicationContext ioc =
                new ClassPathXmlApplicationContext("classpath:Person.xml");
        Person lisi = (Person) ioc.getBean("lisi");
        System.out.println(lisi);
    }



}
