package com.jiehfut.issmspringbootexample;

import com.jiehfut.issmspringbootexample.properties.DogProperties;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
// @EnableConfigurationProperties(DogProperties.class) 开始该类的属性绑定并且将其注入容器中
public class ISsmSpringbootExampleApplication {

    public static void main(String[] args) {

        // SpringApplication.run(ISsmSpringbootExampleApplication.class, args);

        /**
         * 其他的启动 springboot 项目的办法
         */
        // 1.创建 spring 应用
        SpringApplication application = new SpringApplication(ISsmSpringbootExampleApplication.class);
        // 2.设置一些属性（如监听器，Banner，环境变量...）
        application.setBannerMode(Banner.Mode.OFF);
        // 3.启动应用
        application.run(args);
    }


    /**
     * 其他的启动项目的办法
     * @param args
     */
    public static void main1(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder();
        builder.sources(ISsmSpringbootExampleApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .environment(null)
                .run(args);
    }


}
