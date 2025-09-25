package com.jiehfut.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * ClassName: ServletRequest
 * Package: com.jiehfut.servlet
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/9/26 2:56
 * @Version 1.0
 */

@WebServlet(value = "/filterchain", name = "alias")
public class RequestServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("filterchain service arrive ...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write("filterchain service");
    }
}
