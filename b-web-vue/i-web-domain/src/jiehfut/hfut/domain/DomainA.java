package jiehfut.hfut.domain;

import jakarta.servlet.RequestDispatcher;
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

@WebServlet("/domaina")
public class DomainA extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.向请求域存放数据
        req.setAttribute("request_domain", "request domain message");
        // 2.向会话域存放数据
        HttpSession session = req.getSession();
        session.setAttribute("session_domain", "session domain message");
        // 3.向应用域存放数据
        ServletContext application = getServletContext();
        application.setAttribute("application_domain", "application domain message");



        // 在自己的一次请求之内获取请求域中的数据
        // 1.请求域
        String request_domain = (String) req.getAttribute("request_domain");
        System.out.println("domaina 请求域拿到的值 request_domain = " + request_domain);


        // 进行请求转发，再由其他请求获取请求域中的数据
        RequestDispatcher domainc = req.getRequestDispatcher("domainc");
        domainc.forward(req, resp);

    }


    /**

     google 先来三次请求：
     http://localhost:8080/domaina
     http://localhost:8080/domainb
     http://localhost:8080/domainc

     edge 再来一次请求（意味着不同会话）
     http://localhost:8080/domainb

     google domaina 请求域拿到的值 request_domain = request domain message
     google domainc 请求域拿到的值 request_domain = request domain message
     google domainb 其他请求域拿到的值 request_domain = null
     google domainb 会话域拿到的值 session_domain = session domain message
     google domainb 应用域拿到的值 application_domain = application domain message
     google domainc 请求域拿到的值 request_domain = null


     edge domainb 其他请求域拿到的值 request_domain = null
     edge domainb 会话域拿到的值 session_domain = null
     edge domainb 应用域拿到的值 application_domain = application domain message



     请求域内一般放本次请求业务有关的数据,如:查询到的所有的部门信息
     会话域内一般放本次会话的客户端状态有关的数据,如:当前客户端登录的用户
     应用域一般放本程序应用有关的数据，如:Spring框架的IOC容器

     */
}
