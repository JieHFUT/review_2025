package com.jiehfut.assmioc.config;

import com.jiehfut.assmioc.datasource.DataSources;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


// @Profile("dev") // 整体激活，如果 dev 环境没有激活，整个类都没有作用
@Configuration
public class DataSourcesConfig {
    /**
     * 使用条件注解来实现在某种环境下只会激活一个组件 => @Conditional
     * 也可以使用 @Profile("环境标识") 注解来实现，里面的环境标识被激活的时候就会将组件加入容器
     * @return
     *
     * 1.定义环境标识：dev test prod
     * 2.激活环境标识：明确的告诉 spring 当前处于什么环境，不声明就是 Default 环境标识
     * 3.
     */

    @Bean
    @Profile({"dev", "default"})
    public DataSources dev(){
        DataSources dataSources = new DataSources();
        dataSources.setUrl("jdbc:mysql://127.0.0.1:3306/dev");
        dataSources.setUsername("root");
        dataSources.setPassword("root");
        return dataSources;
    }

    @Bean
    @Profile("test")
    public DataSources test(){
        DataSources dataSources = new DataSources();
        dataSources.setUrl("jdbc:mysql://127.0.0.1:3306/test");
        dataSources.setUsername("root");
        dataSources.setPassword("root");
        return dataSources;
    }

    @Bean
    @Profile("prod")
    public DataSources prod(){
        DataSources dataSources = new DataSources();
        dataSources.setUrl("jdbc:mysql://127.0.0.1:3306/prod");
        dataSources.setUsername("root");
        dataSources.setPassword("root");
        return dataSources;
    }

}
