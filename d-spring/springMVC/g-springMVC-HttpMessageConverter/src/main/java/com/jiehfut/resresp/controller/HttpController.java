package com.jiehfut.resresp.controller;


import com.jiehfut.resresp.bean.User;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class HttpController {


    // 测试请求体信息 @RequestBody
    @RequestMapping(value = "/testRequestBody", method = RequestMethod.POST)
    public String testRequestBody(@RequestBody String requestBody) {
        System.out.println("请求体：requestBody = " + requestBody);
        // 请求体：requestBody = username=jieHFUT&password=1234
        // 无论是 GET && POST 请求，请求只是传输数据的位置不一样，但是传输数据的格式是一样的
        return "success";
    }



    // 测试请求 RequestEntity<T> 获取一个完整的请求报文（包括请求头，请求行，请求体）
    @RequestMapping(value = "/testRequestEntity", method = RequestMethod.POST)
    public String testRequestEntity(RequestEntity<String> requestEntity) {
        // 当前 RequestEntity 表示整个报文信息
        HttpHeaders httpHeaders = requestEntity.getHeaders();
        System.out.println("请求头 httpHeaders = " + httpHeaders);

        String body = requestEntity.getBody();
        System.out.println("请求体 body = " + body);
        return "success";
        // 请求头 httpHeaders = [host:"localhost:8080", connection:"keep-alive", content-length:"30", cache-control:"max-age=0", sec-ch-ua:""Google Chrome";v="131", "Chromium";v="131", "Not_A Brand";v="24"", sec-ch-ua-mobile:"?0", sec-ch-ua-platform:""Windows"", origin:"http://localhost:8080", upgrade-insecure-requests:"1", user-agent:"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36", accept:"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7", sec-fetch-site:"same-origin", sec-fetch-mode:"navigate", sec-fetch-user:"?1", sec-fetch-dest:"document", referer:"http://localhost:8080/", accept-encoding:"gzip, deflate, br, zstd", accept-language:"zh-CN,zh;q=0.9", cookie:"Idea-8311ade3=de9e0354-4fcc-4bb3-bce5-4b2b190c1790", Content-Type:"application/x-www-form-urlencoded;charset=UTF-8"]
        // 请求体 body = username=jieHFUT&password=1234
    }



    // 响应数据给浏览器
    // 原生的向浏览器反馈数据
    @RequestMapping(value = "/testResponse")
    public void testResponse(HttpServletResponse response) throws IOException {
        // 返回 String 的时候 return null;

        // response.getWriter().print();// 可以响应任意的数据（字符串，Object，整数.....）
        // response.getWriter().write();// 可以返回字符串，整数，char[]
        response.getWriter().print("testResponse!");// 将打印的内容作为响应报文的响应体，就像重定向视图将视图资源作为响应体
    }


    // springMVC 向浏览器响应数据
    @ResponseBody
    @RequestMapping(value = "/testResponseBody")
    public String testResponseBody() {
        // 有 @ResponseBody 注解的控制方法，下面的 "success" 不是转发到视图，而是响应字符串到浏览器
        return "success";
    }


    // springMVC 向浏览器响应 User 对象
    @ResponseBody
    @RequestMapping(value = "/testResponseUser")
    public User testResponseUser() {
        // 向浏览器直接返回一个对象，此时控制方法的返回值就是我们要返回到浏览器的数据
        User user = new User(1,0,81,"123456","张三");
        return user;
        // 浏览器不会直接展示 java 对象，如果浏览器不对 java 对象进行处理，后端响应 java 对象，若前端不处理报 406 错误，
        // 浏览器需要将其转换为 JSON 显示，或者在后端，我们将 java 对象转换成为 JSON 格式再响应到浏览器
        // 后端将 java 对象转换成为 JSON 格式需要引入 jackson 依赖

        /**
         * 在 springMVC中，java 对象转换成为 JSON 格式字符串
         *
         * 1.导入 jackson 依赖 jackson-databind
         * 2.开始 mvc 的注解驱动 <mvc:annotation-driven/>
         * 3.在控制层方法上使用 @ResponseBody 标识
         * 4.直接将 java 对象作为控制器方法的返回值响应给前端
         *
         * 这上面四步就会将一个 java 对象转换成为 JSON 格式字符串响应给前端
         */
    }




    /**
     * 在 springMVC中，处理 ajax（ajax && axios）
     */
    @ResponseBody
    @RequestMapping(value = "/textAxios")
    public String textAxios(String username, String password) {
        System.out.println("username = " + username + ", password = " + password);
        return "hello, axios";
    }






}
