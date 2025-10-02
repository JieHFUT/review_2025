package com.jiehfut.bspringbootinitializr.bean;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Data
@ConfigurationProperties("conf1")
public class Conf1 {

    private Integer id;
    private String name;
    private Integer age;

}
