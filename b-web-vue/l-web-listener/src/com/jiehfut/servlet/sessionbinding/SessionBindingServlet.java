package com.jiehfut.servlet.sessionbinding;

import com.jiehfut.listener.sessionbinding.SessionBindingListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/sessionbinding")
public class SessionBindingServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();


        SessionBindingListener sessionBindingListener = new SessionBindingListener() {};

        // 当这个对象放进 session 对象的时候，触发监视器
        req.setAttribute("sessionBindingListener", sessionBindingListener);
        // 当这个对象从 session 对象移除的时候，触发监视器
        req.removeAttribute("sessionBindingListener");
    }
}
