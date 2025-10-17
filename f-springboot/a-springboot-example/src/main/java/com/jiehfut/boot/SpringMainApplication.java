package com.jiehfut.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication // 标明这是 springboot 的主程序
public class SpringMainApplication {
    // 需要什么功能，直接导入对应场景
    public static void main(String[] args) {
        SpringApplication.run(SpringMainApplication.class, args);
    }
    

}
