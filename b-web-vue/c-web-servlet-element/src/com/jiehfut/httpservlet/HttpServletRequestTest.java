package com.jiehfut.httpservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

// 0001 请求行 请求头 请求体
@WebServlet("/httpServletRequest")
public class HttpServletRequestTest extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 请求行相关的  GET POST URL http/1.1
        // 也就是 URL 中的各个数据
        String method = req.getMethod();    // 获取请求方式
        String scheme = req.getScheme();    // 获取请求的协议
        String protocol = req.getProtocol();// 获取协议以及版本号
        String uri = req.getRequestURI();   // 获取项目内的资源路径
        String url = req.getRequestURL().toString();// 获取请求的项目内资源的完整的路径

        System.out.println("获取请求方式 method = " + method);
        System.out.println("获取请求的协议 scheme = " + scheme);
        System.out.println("获取协议以及版本号 protocol = " + protocol);
        System.out.println("获取项目内的资源路径 uri = " + uri);
        System.out.println("获取请求的项目内资源的完整的路径 url = " + url);

        /**
         获取请求方式 method = GET
         获取请求的协议 scheme = http
         获取协议以及版本号 protocol = HTTP/1.1
         获取项目内的资源路径 uri = /servletelement/httpServlet
         获取请求的项目内资源的完整的路径 url = http://localhost:8080/servletelement/httpServlet
         URI: 统一资源标识符  interface URI()              资源定位的要求和规范
         URL: 统一资源定位符  class URL implements URI{}   具体的某一个资源的定位语法
              http://ip:port/demo/xxx.html
         */

        int appPort = req.getLocalPort();      // 本应用容器（Tomcat）的端口号
        int serverPort = req.getServerPort();  // proxy 代理
        int clientPort = req.getRemotePort();  // 客户端软件的端口号

        System.out.println("本应用容器（Tomcat）的端口号 appPort = " + appPort);
        System.out.println("proxy 代理 serverPort = " + serverPort);
        System.out.println("客户端软件的端口号 clientPort = " + clientPort);

        /**
         如 Tomcat 下面跑了几个应用：example detectusername servletelement
         则 example detectusername servletelement 都叫做本应用，本应用的容器是 Tomcat

         但是有可能存在代理的情况，浏览器向服务器发送请求的IP和端口，可能通过代理

         本应用容器（Tomcat）的端口号 appPort = 8080
         proxy 代理 serverPort = 8080
         客户端软件的端口号 clientPort = 42238
         */









        // 请求头相关的  key:value ...
        // 1.根据请求头的名字单独拿到 value
        String accept = req.getHeader("Accept");
        System.out.println("accept = " + accept);

        // 2.获取所有的请求头
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.println(headerName + " = " + req.getHeader(headerName));
        }

//        host = localhost:8080
//        connection = keep-alive
//        cache-control = max-age=0
//        sec-ch-ua = "Google Chrome";v="131", "Chromium";v="131", "Not_A Brand";v="24"
//        sec-ch-ua-mobile = ?0
//        sec-ch-ua-platform = "Windows"
//        upgrade-insecure-requests = 1
//        user-agent = Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36
//        accept = text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7
//        sec-fetch-site = none
//        sec-fetch-mode = navigate
//        sec-fetch-user = ?1
//        sec-fetch-dest = document
//        accept-encoding = gzip, deflate, br, zstd
//        accept-language = zh-CN,zh;q=0.9

        // 请求体相关的

    }
}
