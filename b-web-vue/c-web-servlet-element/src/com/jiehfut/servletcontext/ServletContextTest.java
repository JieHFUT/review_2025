package com.jiehfut.servletcontext;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;


@WebServlet("/servletContext")
public class ServletContextTest extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("获取 servletContext 对象的参数");

        // 获取 servletContext 下面三个本质上是同一个对象，应用域
        ServletContext servletContext1 = getServletContext();
        ServletContext servletContext2 = req.getServletContext();
        ServletContext servletContext3 = getServletConfig().getServletContext();

        // 获取配置的公共参数
        String key = "key";
        String value = servletContext1.getInitParameter(key);
        System.out.println(key + " = " + value);


        // 统一获得 key 值以后，再获取 value
        Enumeration<String> initParameterNames = servletContext1.getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String initParameterName = initParameterNames.nextElement();
            System.out.println(initParameterName + " = " + servletContext1.getInitParameter(initParameterName));
        }

    }
}
