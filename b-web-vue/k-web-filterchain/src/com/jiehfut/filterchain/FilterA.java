package com.jiehfut.filterchain;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;

import java.io.IOException;

// 全注解模式
@WebFilter(
        //
        filterName = "loggingFilter",
        // 初始配置参数
        initParams = {@WebInitParam(name="dateTimePattern",value="yyyy-MM-dd HH:mm:ss")},
        // 过滤的项目的多个路径
        urlPatterns = {"/filterchain", "*.html"},
        // 过滤的别名
        servletNames = {"alias"}
)
public class FilterA implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("filtera before dofilter invoked");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("filtera after dofilter invoked");
    }
}
