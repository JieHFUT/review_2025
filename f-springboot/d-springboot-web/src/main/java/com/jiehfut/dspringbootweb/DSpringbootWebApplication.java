package com.jiehfut.dspringbootweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DSpringbootWebApplication {

    /**
     * 1.spring-boot-starter-web 场景
     * 2.引入了 spring-boot-starter 场景 => 引入了 spring-boot-autoconfigure 功能
     * 3.@SpringBootApplication 启动的时候开启 @EnableAutoConfiguration
     * 4.@EnableAutoConfiguration 开启自动配置是通过 @Import(AutoConfigurationImportSelector.class) 批量导入组件
     * 5.AutoConfigurationImportSelector 类通过加载文件（META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports）里面的所有组件
     *
     *
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
     * 这些配置类又绑定了配置文件的一堆配置项
     * SpringMVC => spring.mvc
     * Web       => spring.web
     * 文件上传    => spring.servlet.multipart
     * 服务器     => server
     *
     *
     * @param args
     */

    public static void main(String[] args) {

        SpringApplication.run(DSpringbootWebApplication.class, args);
    }

}
