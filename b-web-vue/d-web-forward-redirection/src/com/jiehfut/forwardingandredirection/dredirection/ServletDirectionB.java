package com.jiehfut.forwardingandredirection.dredirection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * ClassName: ServletDirectionB
 * Package: com.jiehfut.forwardingandredirection.dredirection
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/11/22 23:16
 * @Version 1.0
 */

@WebServlet("/servletDirectionB")
public class ServletDirectionB extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("servletDirectionB 已经接收用户请求");

        // 客户端向 A 输入 http://localhost:8080/forwardandredirection/servletDirectionA?key=value
        String value = req.getParameter("key");
        System.out.println("ServletForwardA get key = " + value);
        // 发现拿不到重定向中树 key-value

    }
}
