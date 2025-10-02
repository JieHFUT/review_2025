package com.jiehfut.bspringbootinitializr.config;


import com.alibaba.druid.FastsqlException;
import com.jiehfut.bspringbootinitializr.bean.Conf1;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;


@Import(ArrayList.class) // 2.或者以这种方式进行第三方的组件注入
@Configuration // 声明这是一个配置类
@ComponentScan("com.jiehfut.bspringbootinitializr") // 配置项目的包扫描路径
@EnableConfigurationProperties(Conf1.class) // 该注解有两个作用（一般用于第三方组件导入）:
                                            // 1.开启 Conf1 的组件的配置绑定  2.默认把这个组件放到容器中
public class ApplicationConfig {

    // 1.第三方的组件进行注入
    @Bean
    public FastsqlException fastsqlException() {
        FastsqlException fastsqlException = new FastsqlException();
        return fastsqlException;
    }
    





}
