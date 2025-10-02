package com.jiehfut.bspringbootinitializr.bean;

import lombok.Data;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:conf/conf2.properties") // 声明配置数据来源
@Data                                              // @PropertySource 的意思就是把指定的文件导入容器中，可以将注解放在 app.config 中
public class Conf2 {

    private Integer id;
    private String name;
    private Integer age;
}
