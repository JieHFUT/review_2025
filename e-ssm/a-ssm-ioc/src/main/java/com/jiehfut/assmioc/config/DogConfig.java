package com.jiehfut.assmioc.config;


import com.jiehfut.assmioc.bean.Dog;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DogConfig {

    @Bean("dog")
    @ConditionalOnMissingBean(name = "dahuang") // 判断如果容器中没有这个名字的组件就将该组件注册到容器中
    public Dog dogBean() {
        System.out.println("正在创建 Dog 组件...");
        Dog dog = new Dog();
        dog.setName("dog");
        return dog;
    }


//    @Bean("dahuang")
//    public Dog dahuang() {
//        System.out.println("spring 条件组件正在创建 Dog 组件...");
//        Dog dog = new Dog();
//        dog.setName("dahuang");
//        return dog;
//    }



}
