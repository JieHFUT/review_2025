package com.jiehfut.essmwebmvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestMappingLimitController {


    /**
      @Target({ElementType.TYPE, ElementType.METHOD})
      @Retention(RetentionPolicy.RUNTIME)
      @Documented
      @Mapping
      @Reflective(ControllerMappingReflectiveProcessor.class)
      public @interface RequestMapping {
      String name() default "";

      @AliasFor("path")
      String[] value() default {};

      @AliasFor("value")
      String[] path() default {};

      RequestMethod[] method() default {};
      String[] params() default {};   限制请求参数
      String[] headers() default {};  限制请求头信息
      String[] consumes() default {}; 限制请求参数类型
      String[] produces() default {}; 限制响应参数类型
      }

      路径变量：@PathVariable
      请求限定:
      请求方式：method => GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE;
      请求参数：params
      请求头：headers
      请求内容类型：consumes，浏览器必需携带什么格式的数据（MediaType）
            表单在发送请求的时候：1.multipart/form-data - 文件上传（）
                             2.application/x-www-form-urlencoded - 普通的 key-value 上传（）
                             3.text/plain -
            其他格式：4.application/json
                    5.application/xml
                    6.application/yaml
                    7.image/png
                    8.text/plain;charset=utf-8
                    9.text/html;charset=utf-8
      响应内容类型：produces
    */

    @RequestMapping(value = "/limit-method", method = {RequestMethod.GET, RequestMethod.POST})
    public String limitMethod() {
        return "limit-method";
    }

    @RequestMapping(value = "/param-limt", params = {"username", "age!=18", "!password"})
    public String paramLimit(String username, Integer age) {
        return "param-limt";
    }

    @RequestMapping(value = "/header-limit", headers = {"keya", "!keyb", "keyc=valuec"})
    public String headerLimit() {
        return "header-limit";
    }

    @RequestMapping(value = "/consumes-limit", consumes = {"application/json"})
    public String consumesLimit() {
        return "consumes-limit";
    }

    @RequestMapping(value = "/produces-limit", produces = {"text/plain;charset=utf-8"})
    public String producesLimit() {
        return "produces-limit";
    }


    /**
     * http  默认端口 80
     * https 默认端口 443
     * http://www.example.com:80/path/to/myfile.html?keya=valuea&keyb=valueb#some
     * #some 叫做锚点，前端用来做锚点的，不会发给后端服务器（页面定位使用）
     *
     * get 请求一般携带不了大量数据，这种携带大量数据一般放在 post 请求
     * 浏览器一般称为用户代理 请求头中 - User-Agent
     *
     *
     */

}
