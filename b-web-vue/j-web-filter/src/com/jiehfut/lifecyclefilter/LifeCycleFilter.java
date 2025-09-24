package com.jiehfut.lifecyclefilter;

import jakarta.servlet.*;

import java.io.IOException;

/**
 * 1.构造    构造器  ===============> 项目启动（不像 servlet 可以通过 startup 自己配置什么时候启动）
 * 2.初始化  init()  ==============> 项目启动
 * 3.过滤    doFilter()  =========> 每次请求
 * 4.销毁    destory()  ==========> 服务关闭
 */
public class LifeCycleFilter implements Filter {

    public LifeCycleFilter() {
        System.out.println("构造");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 在 web.xml 中或者注解的形式进行配置
        System.out.println("构造器的初始化信息");
        System.out.println("filter-key = " + filterConfig.getInitParameter("filter-key"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("过滤");
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
        System.out.println("销毁");
    }
}
