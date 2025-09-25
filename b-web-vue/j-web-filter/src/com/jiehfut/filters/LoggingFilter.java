package com.jiehfut.filters;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日志过滤器
 * 主要作用是记录请求的历史，将请求日志打印在控制台上
 *
 * 1.实现 Filter 接口：import jakarta.servlet.Filter;
 * 2.重写过滤方法
 * 3.配置过滤器（web.xml 方式或者注解的形式）
 * 4.
 *
 */
public class LoggingFilter implements Filter {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 过滤请求和响应的方法
     * 1.请求到达目标资源之前，先经过此方法
     * 2.该方法有能力控制请求是否继续向前到达目标资源，也可以在该方法内直接向客户端做响应处理
     * 3.请求到达目标资源后，在给客户端响应之前，还需要经过这个方法（无法控制响应是不是继续往前走，但是可以对响应进行修改）
     * 4.
     * @param servletRequest
     * @param servletResponse
     * @param filterChain 过滤器链，过滤器可以形成一个链条，可以对请求进行连续的过滤
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /**
         * 1.请求到达目标资源之前的代码
         *      （1）判断是否进行登陆
         *      （2）校验权限是否满足
         *
         * 2.放行代码
         *
         * 3.响应之前，HttpServletResponse 这个对象在转换成为响应报文之前的功能代码
         *
         */
        // 参数父转子
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 1.请求到达目标资源之前的代码：请求到达目标之前打印日志 => yyyy-MM-dd HH:mm:ss ***被访问了
        String requestURI = request.getRequestURI();
        String time = simpleDateFormat.format(new Date());
        String beforeLogging = requestURI + " 资源在 " + time + " 被访问了";
        System.out.println(beforeLogging);

        // 2.放行代码
        // 通过 filterChain 持续调用 doFilter()，会将 servletRequest, servletResponse
        // 传递给下一个过滤器，一直到最后一个过滤器，然后到目标资源
        Long startTime = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        Long endTime = System.currentTimeMillis();


        // 3.响应之前，HttpServletResponse 这个对象在转换成为响应报文之前的功能代码：
        // 响应转换称为报文前的打印日志 => *** 资源在 yyyy-MM-dd HH:mm:ss 的请求中耗时 *** 毫秒
        String afterLogging = requestURI + " 资源在 " + time + " 的请求中耗时 " + (endTime-startTime) + " ms";
        System.out.println(afterLogging);
    }


    /**
     / 资源在 2024-11-25 12:23:37 被访问了
     / 资源在 2024-11-25 12:23:37 的请求中耗时 0 ms
     / 资源在 2024-11-25 12:23:37 被访问了
     / 资源在 2024-11-25 12:23:37 的请求中耗时 1 ms
     / 资源在 2024-11-25 12:23:37 被访问了
     / 资源在 2024-11-25 12:23:37 的请求中耗时 0 ms
     上面是 IDEA 帮助打开浏览器时候默认的一些请求

     /filtera 资源在 2024-11-25 12:23:45 被访问了
     filtera service arrive ...
     /filtera 资源在 2024-11-25 12:23:45 的请求中耗时 1006 ms

     */
















}
