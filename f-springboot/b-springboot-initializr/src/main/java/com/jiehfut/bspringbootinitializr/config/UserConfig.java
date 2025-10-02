package com.jiehfut.bspringbootinitializr.config;

import com.jiehfut.bspringbootinitializr.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// @SpringBootConfiguration 作用相同
public class UserConfig {



    /**
     * 注册的组件的默认名字是方法名
     * 如果使用 value 属性声明，组件名称就以 value 为准
     * 组件默认是单实例的 singleson
     */
    @Bean // 使用注解来标注为组件
    public User user() {
        User user = new User();
        user.setId(2L);
        user.setName("user");
        return user;
    }

    @Bean("lisi")
    public User lisi() {
        User user = new User();
        user.setId(3L);
        user.setName("lisi");
        return user;
    }










}
