package com.jiehfut.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JspController {

    /**
     * tomcat 中找默认首页面的时候
     * 1.先找 index.html
     * 2.再找 index.htm
     * 3.再找 index.jsp
     */

    @RequestMapping("/success")
    public String success() {
        // 被视图解析器解析，加上前缀 && 后缀
        // 转化视图（internalResourceView）
        return "success";
    }

    /**
     * 再 jsp 视图控制器中
     * 创建 internalResourceView 视图有两种情况
     *  1.没有前缀和使用请求转发前缀的情况
     * 创建 RedirectView 视图的情况只有一种
     *  1.使用重定向前缀的情况
     */


}
