package com.jiehfut.dspringbootweb2.config;


import com.jiehfut.dspringbootweb2.component.MyYamlHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.concurrent.TimeUnit;

// @EnableWebMvc // 使用该注解会禁止默认配置信息
@Configuration
public class StaticResourcesConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加一个自己定义的拦截器
        // WebMvcConfigurer.super.addInterceptors(registry);

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 保留了以前默认的配置
        // WebMvcConfigurer.super.addResourceHandlers(registry);

        // 自己添加规则（原有的规则还在）
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/first/")
                .addResourceLocations("classpath:/second/")
                .addResourceLocations("classpath:/third/")
                .setCacheControl(CacheControl.maxAge(12, TimeUnit.SECONDS));

    }

    /**
     * 也可以不实现 WebMvcConfigurer 接口，容器中只要有一个 WebMvcConfigurer 组件，在该
     * 组件中配置了底层信息
     *
     * @Configuration
     * public class StaticResourcesConfig {
     *     @Bean
     *     public WebMvcConfigurer webMvcConfigurer() {
     *         return new WebMvcConfigurer() {
     *             @Override
     *             public void addResourceHandlers(ResourceHandlerRegistry registry) {
     *                 registry.addResourceHandler("/static/**")
     *                         .addResourceLocations("classpath:/first/")
     *                         .addResourceLocations("classpath:/second/")
     *                         .addResourceLocations("classpath:/third/")
     *                         .setCacheControl(CacheControl.maxAge(12, TimeUnit.SECONDS));
     *             }
     *         };
     *     }
     * }
     *
     *
     *
     *
     *
     * 为什么容器中放置一个 WebMvcConfigurer 组件就能够配置生效
     * ==> 利用自动注入
     * WebMvcAutoConfiguration 是一个自动配置类，里面有一个内部类 EnableWebMvcConfiguration
     * EnableWebMvcConfiguration 继承了 DelegatingWebMvcConfiguration（这两个都会生效）
     * DelegatingWebMvcConfiguration 该类调用每一个 xxxConfigurer（）里面的方法
     * WebMvcConfigurer 接口里面的方法会被 DelegatingWebMvcConfiguration.configurePathMatch()... 调用
     *
     * 总结就是别人调用 DelegatingWebMvcConfiguration里面的方法来配置底层规则，而它就会调用所有 WebMvcConfigurer
     *
     *
     * @Configuration(proxyBeanMethods = false)
     * public class DelegatingWebMvcConfiguration extends WebMvcConfigurationSupport {
     * 	   private final WebMvcConfigurerComposite configurers = new WebMvcConfigurerComposite();
     * 	   下面利用 DI 将容器中的所有 WebMvcConfigurer都注入进来，List
     * 	   @Autowired(required = false)
     * 	   public void setConfigurers(List<WebMvcConfigurer> configurers) {
     * 	      if (!CollectionUtils.isEmpty(configurers)) {
     * 		  this.configurers.addWebMvcConfigurers(configurers);
     *     }
     * }
     */

    /**
     * 配置一个新的消息转换器
     * 配置一个能够将对象转换为 yaml 对象的 message 转换器
     * 1.添加引用（在 pom.xml 中配置媒体类型支持）
     * 2.编写配置 application.properties，新增一种媒体类型
     * 3.增加一个 MessageConverter 组件（MyYamlHttpMessageConverter）
     * 4.在 WebMvcConfigurer 容器中放置该组件
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MyYamlHttpMessageConverter());
    }


}
