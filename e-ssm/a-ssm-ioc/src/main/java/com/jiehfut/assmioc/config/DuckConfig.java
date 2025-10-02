package com.jiehfut.assmioc.config;


import com.jiehfut.assmioc.bean.Duck;
import com.jiehfut.assmioc.service.UserService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DuckConfig {


    @Bean("ahua")
    public Duck ahua() {
        System.out.println("spring 条件组件正在创建 Duck ahua 组件...");
        Duck duck = new Duck();
        duck.setName("ahua");
        return duck;
    }

    @Bean("duck")
    @ConditionalOnMissingBean(name = "ahua") // 判断如果容器中没有这个名字的组件就将该组件注册到容器中
    public Duck duckBean() {
        System.out.println("正在创建 Duck 组件...");
        Duck duck = new Duck();
        duck.setName("duck");
        return duck;
    }

    @Bean("aliang")
    @ConditionalOnMissingBean(value = UserService.class) // 判断如果容器中没有这个类的组件就将该组件注册到容器中
    public Duck aliang() {
        System.out.println("spring 条件组件正在创建 Duck aliang 组件...");
        Duck duck = new Duck();
        duck.setName("aliang");
        return duck;
    }

    @Bean("ada")
    @ConditionalOnResource(resources = "classpath:aaa.abc") // 判断如果容器中有这个资源就将该组件注册到容器中
    public Duck ada() {
        System.out.println("spring 条件组件正在创建 Duck ada 组件...");
        Duck duck = new Duck();
        duck.setName("ada");
        return duck;
    }





}
