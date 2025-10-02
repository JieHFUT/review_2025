package com.jiehfut.assmioc.config;


import com.jiehfut.assmioc.bean.Cat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration
public class CatConfig {

    @Bean("first-cat")
    @Scope("prototype")
    public Cat firstCat() {
        System.out.println("first-cat 组件正在创建...");
        return new Cat("first-cat", 4);
    }



    @Lazy
    @Bean("second-cat")
    @Scope("singleton")
    public Cat secondCat() {
        System.out.println("second-cat 组件正在创建...");
        return new Cat("second-cat", 3);
    }




}
