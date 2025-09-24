package jiehfut.hfut.domain;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


/**
 * 1.void setAttribute(String key, Object value)  向域中存储或者修改数据
 * 2.Object getAttribute(String key)              获得域中的数据
 * 3.void removeAttribute(String key)             移除域中的数据
 */

@WebServlet("/domainb")
public class DomainB extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 从三个域中拿取数据

        // 1.请求域
        String request_domain = (String) req.getAttribute("request_domain");
        System.out.println("domainb 其他请求域拿到的值 request_domain = " + request_domain);

        // 2.会话域
        HttpSession session = req.getSession();
        String session_domain = (String) session.getAttribute("session_domain");
        System.out.println("domainb 会话域拿到的值 session_domain = " + session_domain);

        // 3.应用域
        ServletContext application = getServletContext();
        String application_domain = (String) application.getAttribute("application_domain");
        System.out.println("domainb 应用域拿到的值 application_domain = " + application_domain);
    }
}
