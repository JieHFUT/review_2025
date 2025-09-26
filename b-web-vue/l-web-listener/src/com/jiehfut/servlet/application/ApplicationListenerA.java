package com.jiehfut.servlet.application;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/applicationa")
public class ApplicationListenerA extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 向应用域中放入数据
        ServletContext application = getServletContext();
        application.setAttribute("keya", "valuea");




        // 创建 session 对象
        HttpSession session = req.getSession();
        // 手动销毁 session
        session.invalidate();
    }
}
