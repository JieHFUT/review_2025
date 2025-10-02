package com.jiehfut.assmioc.config;


import com.jiehfut.assmioc.bean.SpringLifeCycle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringLifeCycleConfig {


    /**
     * 声明组件的生命周期
     * @return
     */
    @Bean(value = "s-lifecycle", initMethod = "initLifeCycle", destroyMethod = "destroyLifeCycle")
    public SpringLifeCycle springLifeCycle() {
        return new SpringLifeCycle();
    }




}
