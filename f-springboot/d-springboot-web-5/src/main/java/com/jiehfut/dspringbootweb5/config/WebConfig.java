package com.jiehfut.dspringbootweb5.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 前面接管 SpringMVC
 * WebMvcAutoConfiguration 类 => web 场景的自动配置类
 *
 */
@Configuration
@EnableWebMvc // 禁用 mvc 的默认功能
public class WebConfig implements WebMvcConfigurer {


}
