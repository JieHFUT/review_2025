package com.jiehfut.config;

import com.jiehfut.interceptor.TestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.List;
import java.util.Properties;

/**
 * ClassName: WebConfig
 * Package: com.jiehfut.config
 * Description:
 *
 * 该类使用 @Configuration 注解，用来代替 springMVC 的组件
 * 1.扫描组件
 * 2.视图解析器
 * 3.view-controller
 * 4.default-servlet-handler
 * 5.mvc 注解驱动
 * 6.文件上传解析器
 * 7.异常处理
 * 8.拦截器
 *
 * @Author jieHFUT
 * @Create 2024/12/9 0:50
 * @Version 1.0
 */
// 声明该类是配置类，将其加载进IOC容器中
@Configuration
// 1.扫描组件
@ComponentScan("com.jiehfut.controller")
// 5.mvc 注解驱动
@EnableWebMvc
// 4.default-servlet-handler 通过实现接口 WebMvcConfigurer
// 8.拦截器 通过实现接口 WebMvcConfigurer
// 3.view-controller 通过实现接口 WebMvcConfigurer
public class WebConfig implements WebMvcConfigurer {

    /**
     * 配置生成模板解析器
     * <bean class="org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver">
     * <bean class="org.thymeleaf.spring6.SpringTemplateEngine">
     * <bean id="viewResolver" class="org.thymeleaf.spring6.view.ThymeleafViewResolver">
     * @return
     */
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver springResourceTemplateResolver = new SpringResourceTemplateResolver();
        springResourceTemplateResolver.setApplicationContext(ContextLoader.getCurrentWebApplicationContext());
        springResourceTemplateResolver.setPrefix("/WEB-INF/templates/");
        springResourceTemplateResolver.setSuffix(".html");
        springResourceTemplateResolver.setCharacterEncoding("UTF-8");
        springResourceTemplateResolver.setTemplateMode(TemplateMode.HTML);
        return springResourceTemplateResolver;
    }



    //生成模板引擎并为模板引擎注入模板解析器
    @Bean
    public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }


    //生成视图解析器并未解析器注入模板引擎
    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setTemplateEngine(templateEngine);
        return viewResolver;
    }



    // 设置默认的 servlet 可用
    // 4.default-servlet-handler
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


    // 添加拦截器对象
    // 8.拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        TestInterceptor testInterceptor = new TestInterceptor();
        // 添加拦截规则
        registry.addInterceptor(testInterceptor).addPathPatterns("/**");
        // 添加去除拦截的路径
        registry.addInterceptor(testInterceptor).excludePathPatterns("/");

        // .... 下面可以添加第二个 第三个.. 拦截器
    }


    // 添加视图控制器
    // 3.view-controller
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 访问路由 "/hello" 跳转到视图 hello.html
        registry.addViewController("/hello").setViewName("hello");
    }


    // 异常处理
    // 7.异常处理
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
        // 设置异常的视图映射
        Properties properties = new Properties();
        properties.setProperty("java.lang.ArithmeticException", "error");
        exceptionResolver.setExceptionMappings(properties);
        // 在请求域中共享信息的键
        exceptionResolver.setExceptionAttribute("exception");
        resolvers.add(exceptionResolver);
    }

    // 文件上传解析器
    // 6.文件上传解析器
    @Bean
    public MultipartResolver multipartResolver() {
        StandardServletMultipartResolver multipartResolver = new StandardServletMultipartResolver();
        return multipartResolver;
    }


}
