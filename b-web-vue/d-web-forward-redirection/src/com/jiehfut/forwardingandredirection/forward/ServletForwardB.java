package com.jiehfut.forwardingandredirection.forward;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/servletForwardB")
public class ServletForwardB extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletForwardB 执行了");

        // http://localhost:8080/orwardandredirection/servletForwardA?key=value
        String value = req.getParameter("key");
        System.out.println("ServletForwardB get key = " + value);




    }
}
