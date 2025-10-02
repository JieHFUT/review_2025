package com.jiehfut.hssmwebmvcpractice.config;

import com.jiehfut.hssmwebmvcpractice.interceptor.HandlerInterceptorForThisProject1;
import com.jiehfut.hssmwebmvcpractice.interceptor.HandlerInterceptorForThisProject2;
import com.jiehfut.hssmwebmvcpractice.interceptor.HandlerInterceptorForThisProject3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 容器中需要有一个组件：WebMvcConfigurer
 * 1.使用 @Bean注解注入一个组件
 * 2.实现该组件
 *
 * 拦截器：addInterceptors()
 */
@Configuration
public class SpringMvcConfig {

    // 注入自己设置的拦截器
    @Autowired
    private HandlerInterceptorForThisProject1 handlerInterceptorForThisProject1;
    @Autowired
    private HandlerInterceptorForThisProject2 handlerInterceptorForThisProject2;
    @Autowired
    private HandlerInterceptorForThisProject3 handlerInterceptorForThisProject3;

    @Bean
    WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(handlerInterceptorForThisProject1)
                        .addPathPatterns("/**") // 拦截所有请求
                        .excludePathPatterns("/login");
                registry.addInterceptor(handlerInterceptorForThisProject2)
                        .addPathPatterns("/**")
                        .excludePathPatterns("/login");
                registry.addInterceptor(handlerInterceptorForThisProject3)
                        .addPathPatterns("/**")
                        .excludePathPatterns("/login");
            }
            /**
             * 全放行
             001-控制器方法执行之前 ====> 拦截器 preHandle 放行
             002-控制器方法执行之前 ====> 拦截器 preHandle 放行
             003-控制器方法执行之前 ====> 拦截器 preHandle 放行
             controller - 获取所有的用户信息...
             003-控制器方法执行之后 ====> 拦截器 postHandle
             002-控制器方法执行之后 ====> 拦截器 postHandle
             001-控制器方法执行之后 ====> 拦截器 postHandle
             003-控制器方法响应之前 ====> 拦截器 afterCompletion
             002-控制器方法响应之前 ====> 拦截器 afterCompletion
             001-控制器方法响应之前 ====> 拦截器 afterCompletion

             * 部分不放行（ 002不放行 ）
             * 任意一个不去放行，所有的 postHandle 都不会执行
             * 但是已经放行过的的拦截器的 afterCompletion 会执行
             *
             001-控制器方法执行之前 ====> 拦截器 preHandle 放行
             002-控制器方法执行之前 ====> 拦截器 preHandle 放行
             001-控制器方法响应之前 ====> 拦截器 afterCompletion


             * 002 的 postHandle 异常
             001-控制器方法执行之前 ====> 拦截器 preHandle 放行
             002-控制器方法执行之前 ====> 拦截器 preHandle 放行
             003-控制器方法执行之前 ====> 拦截器 preHandle 放行
             003-控制器方法执行之后 ====> 拦截器 postHandle
             002-控制器方法执行之后 ====> 拦截器 postHandle (其方法内异常)
             003-控制器方法响应之前 ====> 拦截器 afterCompletion
             002-控制器方法响应之前 ====> 拦截器 afterCompletion
             001-控制器方法响应之前 ====> 拦截器 afterCompletion
             */
        };
    }
}
