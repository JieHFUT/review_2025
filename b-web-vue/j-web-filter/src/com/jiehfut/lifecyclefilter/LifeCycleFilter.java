package com.jiehfut.lifecyclefilter;

import jakarta.servlet.*;

import java.io.IOException;

/**
 * 0003
 *
 *
 * 1.构造    构造器  ===============> 项目启动执行（不像 servlet 可以通过 startup 自己配置什么时候启动）
 * 2.初始化  init()  ==============> 项目启动执行
 * 3.过滤    doFilter()  =========> 每次请求都执行
 * 4.销毁    destory()  ==========> 服务关闭执行
 */
public class LifeCycleFilter implements Filter {

    public LifeCycleFilter() {
        System.out.println("构造");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // FilterConfig 的作用是获取过滤器的初始化的信息的，这些信息在 web.xml 中配置
        // 这些初始化信息会被 Tomcat 读取后创建一个 FilterConfig 对象，将其放置在初始化的时候
        // 在 web.xml 中或者注解的形式进行配置
        System.out.println("构造器的初始化信息");
        System.out.println("filter-key = " + filterConfig.getInitParameter("filter-key"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("过滤方法");
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
        System.out.println("销毁");
    }


    /**
     * /j_web_filter_war_exploded/filtera 资源在 2025-09-26 02:20:53 被访问了
     * 过滤方法
     * filtera service arrive ...
     * /j_web_filter_war_exploded/filtera 资源在 2025-09-26 02:20:53 的请求中耗时 1009 ms
     */
}
