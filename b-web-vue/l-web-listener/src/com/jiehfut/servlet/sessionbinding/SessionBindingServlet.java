package com.jiehfut.servlet.sessionbinding;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/sessionbinding")
public class SessionBindingServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();


        HttpSessionBindingListener httpSessionBindingListener = new HttpSessionBindingListener() {};

        // 当这个对象放进 session 对象的时候，触发监视器
        req.setAttribute("httpSessionBindingListener", httpSessionBindingListener);
        // 当这个对象从 session 对象移除的时候，触发监视器
        req.removeAttribute("httpSessionBindingListener");
    }
}
