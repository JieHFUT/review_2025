package com.jiehfut.servletinterface;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/test")
public class Test extends HttpServlet {



    /**
     * 1.如果不重写 service 方法，那么重写 doGet && doPost 方法
     * 2.在 tomcat 调用 httpservlet 中的 service 方法的时候，也会走自己写的 servlet 方法
     *
     * 3.建议在处理前后端交互的时候，重写 doGet && doPost 方法
     * 4.后续使用 SpringMVC 框架以后，就无需去继承 HttpServlet，也无需去重写 doGet && doPost 方法
     * 5.如果 doGet && doPost 方法中处理的代码都一样，就可以直接调用
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doget");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost");
    }
}
