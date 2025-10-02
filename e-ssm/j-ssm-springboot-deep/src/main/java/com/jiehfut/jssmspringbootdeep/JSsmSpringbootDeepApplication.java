package com.jiehfut.jssmspringbootdeep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JSsmSpringbootDeepApplication {

    public static void main(String[] args) {

        /**
         * @Profiles("dev") 注解的作用
         * 基于环境标识进行判断，利用环境隔离机制来自动配置当前运行环境
         *
         * 1.定义环境，dev, test, prod
         * 2.定义这个环境下哪些组件生效，哪些配置生效 ?
         *      （1）给组件使用 @Profiles("dev")，
         *      （2）生效哪些配置，application-dev.properties 使用
         *      （3）激活这些环境，在 application.properties 中使用 spring.profiles.active=dev
         *      （4）激活某一个环境，某一个环境下的组件和配置就会生效（application.properties 是任意环境都激活的）
         *      （5）也可以使用命令行 java -jar xxx.jar --spring.profiles.active=dev
         * 一般 include 是包含一些通用配置，基础的配置 mybatis、log、xxx：写到包含环境中
         * 需要动态切换变化的 db、redis：写到激活的环境中
         *
         * 3.环境分组功能
         *
         *
         *
         */
        SpringApplication.run(JSsmSpringbootDeepApplication.class, args);

        /**
         * 暴露项目运行的时候的所有指标
         * 使用 springboot 提供的 actuator 模块快速暴露项目运行时候指标
         * 导入场景：spring-boot-starter-actuator
         * 导入以后访问：http://localhost:8080/actuator
         * 开启监控指标配置：
         *
         *
         */

    }

}
