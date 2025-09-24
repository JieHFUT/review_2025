package com.jiehfut.servlet.sessionactivationlistener;

import com.jiehfut.listener.sessionactivationlistener.SessionActivationListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/sessionbinding")
public class SessionActivationServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        // 让当前这个 session 对象的 钝化&&活化 被监听
        SessionActivationListener listener = new SessionActivationListener();


        // 把对象放进 session 对象
        // 这样这个 session 对象进行 钝化&&活化 的时候就会被监听到
        session.setAttribute("listener", listener);

        // 但是如果想要项目可以被钝化&&活化 => 需要进行配置
        // 在 web/MATE-INF/Context.xml 配置钝化
    }
}
