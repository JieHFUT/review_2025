package com.jiehfut.bspringbootinitializr.bean;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Repository;

@Data
@Repository
@ConfigurationProperties(prefix = "conf")  // 绑定 application.properties 配置文件中的 conf 属性
                                           // 该注解可以放在类上，也可以将其放在方法上
public class Conf {

    private Integer id;
    private String name;
    private Integer age;

}
