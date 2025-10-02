package com.jiehfut.dspringbootweb2.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.util.UrlPathHelper;

/**
     *：表示任意数量的字符。
     ?：表示任意一个字符。
     **：表示任意数量的目录。
     {}：表示一个命名的模式占位符。
     []：表示字符集合，例如[a-z]表示小写字母。

     AntPathMatcher    ** 可以使用在中间
     PathPatternParser 效率高，代价小，但是需要注意的是 ** 不能使用在中间，只能使用在最后
*/

@RestController
public class PathController {

    // 1.一个类可以拥有一个日志类，拿到当前类的日志记录器，可以使用注解注入
    Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/a*/b?/{p1:[a-f]+}/**")
    public String hello(HttpServletRequest request,
                        @PathVariable("p1") String path) {

        logger.info("路径变量p1： {}", path);
        //获取请求路径 http://localhost:8080/awwu/bo/aabbcdefffb/aaa/bbb
        String uri = request.getRequestURI();
        // uri = /awwu/bo/aabbcdefffb/aaa/bbb
        return uri;
    }


/*



   public interface WebMvcConfigurer {
       default void configurePathMatch(PathMatchConfigurer configurer) {}
   }

   public class WebMvcAutoConfiguration {

       public static class WebMvcAutoConfigurationAdapter{
       ==================================================================
           configurePathMatch() 如下
           在这个方法中，WebMvcProperties mvcProperties 中匹配策略是哪一个，就使用哪一个
           在 WebMvcProperties 中，默认是枚举类型，默认使用 PATH_PATTERN_PARSER，如果你在
           配置中配置了 spring.mvc.pathmatch.matching-strategy=path_pattern_parser，就使用配置的匹配策略
            	public enum MatchingStrategy {
                    ANT_PATH_MATCHER,
                    PATH_PATTERN_PARSER
                }
       ===================================================================
       }
   }



    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        if (this.mvcProperties.getPathmatch()
                .getMatchingStrategy() == WebMvcProperties.MatchingStrategy.ANT_PATH_MATCHER) {
            configurer.setPathMatcher(new AntPathMatcher());
            this.dispatcherServletPath.ifAvailable((dispatcherPath) -> {
                String servletUrlMapping = dispatcherPath.getServletUrlMapping();
                if (servletUrlMapping.equals("/") && singleDispatcherServlet()) {
                    UrlPathHelper urlPathHelper = new UrlPathHelper();
                    urlPathHelper.setAlwaysUseFullPath(true);
                    configurer.setUrlPathHelper(urlPathHelper);
                }
            });
        }
    }
*/

}
