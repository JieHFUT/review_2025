package com.jiehfut.springmybatisplusmultidatasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.jiehfut.springmybatisplusmultidatasource.mapper")
public class SpringMybatisPlusMultiDatasourceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ioc =
                SpringApplication.run(SpringMybatisPlusMultiDatasourceApplication.class, args);


    }

}
