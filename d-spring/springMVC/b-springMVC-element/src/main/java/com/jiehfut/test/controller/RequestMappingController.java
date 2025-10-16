package com.jiehfut.test.controller;


import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/class")
public class RequestMappingController {


/*    @RequestMapping("/")  // 要保证 RequestMapping 的请求路径是唯一的，除非请求方式设置不相同
    public String index() {
        // 返回视图名称
        return "target";
    }*/




    // 1.根据请求地址来匹配请求 404
    // 2.根据请求方式来匹配请求 405
    // 3.根据请求参数来匹配请求 400
    // 4.根据请求头信息匹配请求 404



    // 1.根据请求地址来匹配请求
    @RequestMapping("/testRequestMapping") // 直接和浏览器的请求创建关联，进行匹配
    public String testRequestMapping() {
        // 若是类上也添加了 RequestMapping，需要在上下文后访问 "/class/testRequestMapping"
        return "success";
    }


    // 1.根据请求地址来匹配请求
    @RequestMapping(
            value = {"/path1", "/path2", "/path3"})
    public String pathValueArray() {
        // value 属性是一个数组值，且请求中必需有 value 属性
        return "ArrayPath";
    }


    // 1.根据请求地址来匹配请求
    // 2.根据请求方式来匹配请求
    @RequestMapping(
            value = {"/path6", "/path7"},
            method = {RequestMethod.GET, RequestMethod.POST})
    public String pathValueMethod() {
        // 表示当前的请求地址可以是 /path6  /path7
        // 但是请求方式只能是 GET || POST，否则报错 405（请求方式不支持）
        return "success";
    }



    // 1.根据请求地址来匹配请求
    // 2.根据请求方式来匹配请求
    // method 属性是根据请求方式（GET POST...）来匹配请求的，可以使用派生注解
    @GetMapping(value = {"/path4", "/path5"})
    public String methodRequest() {
        // 如果不设置 method 属性，则任何的请求方式都能匹配
        // GetMapping 只会接收 GET 请求
        return "methodRequest";
    }



    // 1.根据请求地址来匹配请求
    // 2.根据请求方式来匹配请求
    @RequestMapping(value = "/testPut", method = RequestMethod.PUT)
    public String testPut() {
        return "success";
    }





    // 1.根据请求地址来匹配请求
    // 2.根据请求方式来匹配请求
    // 3.根据请求参数来匹配请求（参数必须同时满足）
    @RequestMapping(
            value = {"/testParams", "/testparams"},
            method = {RequestMethod.GET, RequestMethod.PUT},
            params = {"username", "!password", "sex=male"}
    )
    public String testParams() {
        // 表示请求中必需携带 username 属性，且不能携带 password 属性，
        // 且必需携带 sex 属性，且其值必需为 male
        return "success";
    }







    // 1.根据请求地址来匹配请求
    // 2.根据请求方式来匹配请求
    // 3.根据请求参数来匹配请求
    // 4.根据请求头信息匹配请求
    @RequestMapping(
            value = {"/testHeader", "/testheader"},
            method = {RequestMethod.GET, RequestMethod.PUT},
            params = {"username", "!password", "sex!=male"},
            headers = {"key1", "!key2", "key3=value3", "key4!=value4"}
    )
    public String testHeader() {
        // 表示请求中必需携带 username 属性，且不能携带 password 属性，
        // 且必需携带 sex 属性，且其值不能为 male
        // 且请求头必需携带 key1，不能携带 key2，必需携带 key3 并且值必需为 value3，且必需携带 key4，且值不能等于 value4
        return "success";
    }






    // springmvc 支持 ant 风格的路径
    @RequestMapping("/a?a/testAnt")
    public String testAnt1() {
        return "success";
    }

//    @RequestMapping("/a*a/testAnt")
//    public String testAnt2() {
//        return "success";
//    }
//    @RequestMapping("/**/a/testAnt")
//    public String testAnt3() {
//        return "success";
//    }

    /**
     * a?a    ? 可以表示任意的单个字符（ / ? 不好使用）
     * a*a    * 可以表示任意的 0个或多个字符
     * a**a   ** 表示任意的一层或多层目录
     */
     // 注意：在使用 ** 时，只能使用 /**/xxx 的方式





    // springmvc 支持路径中的占位符 {} 为占位符
    @RequestMapping("testRESTful/{classes}/{username}")
    public String testRESTful(@PathVariable("classes") Integer classes,
                              @PathVariable("username") String username) {

        // <a th:href="@{class/testRESTful/1/username}">测试RESTful，支持路径中的占位符</a>
        System.out.println("classes:" + classes);
        System.out.println("username:" + username);
        // classes:1
        // username:username
        return "success";
    }



    /**
        @Target(ElementType.PARAMETER)
        @Retention(RetentionPolicy.RUNTIME)
        @Documented
        public @interface PathVariable {
            @AliasFor("name")
            String value() default "";
            @AliasFor("value")
            String name() default "";
            boolean required() default true;

        }
     */



}
