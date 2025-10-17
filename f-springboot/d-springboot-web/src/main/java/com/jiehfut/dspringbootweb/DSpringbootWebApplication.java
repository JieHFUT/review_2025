package com.jiehfut.dspringbootweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DSpringbootWebApplication {

    /**
     * 1.spring-boot-starter-web 场景
     * 2.引入了 spring-boot-starter 场景 => 引入了 spring-boot-autoconfigure 功能
     *
     * 3.@SpringBootApplication 启动的时候开启 @EnableAutoConfiguration
     * 4.@EnableAutoConfiguration 开启自动配置是通过 @Import(AutoConfigurationImportSelector.class) 批量导入组件
     * 5.AutoConfigurationImportSelector 类通过加载文件（META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports）里面的所有组件

       下面是与 web 有关的自动配置类
     * org.springframework.boot.autoconfigure.web.client.RestClientAutoConfiguration
     * org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration
     * org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration
     * org.springframework.boot.autoconfigure.web.reactive.HttpHandlerAutoConfiguration
     * org.springframework.boot.autoconfigure.web.reactive.ReactiveMultipartAutoConfiguration
     * org.springframework.boot.autoconfigure.web.reactive.ReactiveWebServerFactoryAutoConfiguration
     * org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration
     * org.springframework.boot.autoconfigure.web.reactive.WebSessionIdResolverAutoConfiguration
     * org.springframework.boot.autoconfigure.web.reactive.error.ErrorWebFluxAutoConfiguration
     * org.springframework.boot.autoconfigure.web.reactive.function.client.ClientHttpConnectorAutoConfiguration
     * org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration
     * org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration
     * org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration
     * org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration
     * org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration
     * org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration
     * org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
     *
     * 这些自动配置类（@ConditinoalOnxxx）在一定条件下以组件形式注入容器
     *                                       然后其绑定了配置文件（@EnableConfigurationProperties），
     *                                       将一些配置组件 xxxProperties 注入容器中，配置组件有一堆配置项，通过这个注解向容器中注入该配置类并且绑定自己的配置文件
     *                                       配置文件头部通过 @ConfigurationProperties 声明配置类属性绑定的前缀
     *
     *
     * WebMvcAutoConfiguration 在容器中放了两个过滤器
     *                         在容器中放了 WebMvcConfigurer 组件；给 SpringMVC添加各种定制功能
     *                         （请求参数解析器 && 跨域处理 && 格式化器 && 拦截器 && 静态资源处理器 && 返回值处理器 && 视图控制器 && 异步支持器 && 内容协商
     *                           异常解析器 && 消息转换器 && 路径匹配 && 消息转换）
     *
     *
     *
     * 所有的 web 自动配置类会在容器中注入四个属性类（以及一堆其他组件）
     * WebMvcAutoConfiguration 自动配置类  WebMvcProperties MVC 场景 => spring.mvc
     * WebMvcAutoConfiguration 自动配置类  WebProperties Web场景 => spring.web
     * 文件上传 自动配置类  xxxProperties => spring.servlet.multipart
     * 服务器 自动配置类    xxxProperties => server
     *
     *
     *
     * 上面这些个自动配置类在以组件形式注入容器中后，会向容器中注入一些组件以丰富 web 的功能
     * springboot 自动配置了所有的 web 场景，如果我们想要修改一些功能的默认值，
     * 就需要自己写一些配置类覆盖原有的规则（自己写的配置类需要 @Configuration, 配置类需要实现 WebMvcConfigurer 接口，并且不要标注 @EnableWebMvc）
     *
     * @param args
     */

    public static void main(String[] args) {

        SpringApplication.run(DSpringbootWebApplication.class, args);
    }

}
