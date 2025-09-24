package jiehfut.hfut.domain;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/domainc")
public class DomainC extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求转发中的（请求域）的数据
        String request_domain = (String) req.getAttribute("request_domain");
        System.out.println("domainc 请求域拿到的值 request_domain = " + request_domain);
    }
}
