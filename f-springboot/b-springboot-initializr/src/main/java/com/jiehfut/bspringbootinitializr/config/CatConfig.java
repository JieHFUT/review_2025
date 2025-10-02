package com.jiehfut.bspringbootinitializr.config;

import com.jiehfut.bspringbootinitializr.bean.Cat;
import com.jiehfut.bspringbootinitializr.bean.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;

public class CatConfig {

    /**
     * 条件式注入
     * 1.如果存在某一个类 FastsqlException 组件就给容器中注入此组件 Cat - cat1
     *      不存在这个组件就给容器中注入此组件 User - wangwu
     */
    @Bean("cat1")
    @ConditionalOnClass(name = "com.alibaba.druid.FastsqlException")
    public Cat cat1() {
        return new Cat();
    }

    @Bean("wangwu")
    @ConditionalOnMissingClass(value = "com.alibaba.druid.FastsqlException")
    public User wangwu() {
        User user = new User();
        user.setName("wangwu");
        return user;
    }

    /**
     * 条件式注入
     * 2.如果容器中存住某一个组件，就注入该组件 Cat - cat2
     *           不存在某一个组件，就注入组件 User - zhaoliu
     */
    @Bean("cat2")
    @ConditionalOnBean(name = "lisi")
    public Cat cat2() {
        return new Cat();
    }

    @Bean("zhoaliu")
    @ConditionalOnMissingBean(name = "lisi")
    public User zhaoliu() {
        User user = new User();
        user.setName("zhaoliu");
        return user;
    }
    


}