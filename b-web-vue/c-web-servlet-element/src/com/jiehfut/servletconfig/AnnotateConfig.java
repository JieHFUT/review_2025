package com.jiehfut.servletconfig;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;


/**
 * 使用注解来 servlet 类进行参数配置
 */
@WebServlet(
        urlPatterns = "/annotateConfig",
        initParams = {@WebInitParam(name = "keya", value = "valuea"),
                @WebInitParam(name = "keyb", value = "valueb")}
)
public class AnnotateConfig extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfig servletConfig = getServletConfig();
        // 根据参数名获取初始配置信息
        String keya = servletConfig.getInitParameter("keya");
        System.out.println("keya = " + keya);

        // 获取所有的初始参数的名字，返回的是早期的 Iterator
        Enumeration<String> initParameterNames = servletConfig.getInitParameterNames();
        // hasMoreElements 判断有没有下一个参数，如果有就返回 true，没有就返回 false
        while (initParameterNames.hasMoreElements()) {
            // 取出下一个元素，并且向下移动游标
            String initParameterName = initParameterNames.nextElement();
            System.out.println("initParameterName = " + initParameterName);
        }
    }
}
