package com.jiehfut.cspringbootlogging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CSpringbootLoggingApplication {

    /**
     * 每一个 starter 场景都会导入核心场景 spring-boot-starter
     * 核心场景会引入日志的所有功能：spring-boot-starter-logging
     * 默认使用了 logback-classic + org.slf4j 作为默认日志
     * 日志是系统一启动就需要使用的，xxxAutoConfiguration 是系统启动好了注入的一系列组件
     * 所以自动配日志是监听器配置的（ApplicationListener）
     * 日志所有的配置都可以修改配置文件进行修改，以 logging 开头
     *
     * <dependencies>
     *     <dependency>
     *       <groupId>ch.qos.logback</groupId>
     *       <artifactId>logback-classic</artifactId>
     *       <version>1.5.12</version>
     *       <scope>compile</scope>
     *     </dependency>
     *     <dependency>
     *       <groupId>org.apache.logging.log4j</groupId>
     *       <artifactId>log4j-to-slf4j</artifactId>
     *       <version>2.24.3</version>
     *       <scope>compile</scope>
     *     </dependency>
     *     <dependency>
     *       <groupId>org.slf4j</groupId>
     *       <artifactId>jul-to-slf4j</artifactId>
     *       <version>2.0.16</version>
     *       <scope>compile</scope>
     *     </dependency>
     * </dependencies>
     *
     * 日志形式：时间日期-日志级别-进程ID-线程名-类名-消息
     * 2025-01-01T20:20:07.978+08:00  INFO 84372 --- [b-springboot-initializr] [           main] c.j.b.BSpringbootInitializrApplication   : Starting BSpringbootInitializrApplication using Java 17.0.12 with PID 84372 (E:\code\springboot_code\b-springboot-initializr\target\classes started by 34927 in E:\code\springboot_code)
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(CSpringbootLoggingApplication.class, args);

    }

}
