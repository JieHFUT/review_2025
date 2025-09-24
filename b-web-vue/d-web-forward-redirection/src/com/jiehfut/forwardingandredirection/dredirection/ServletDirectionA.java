package com.jiehfut.forwardingandredirection.dredirection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * ClassName: ServletDirectionA
 * Package: com.jiehfut.forwardingandredirection.dredirection
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/11/22 23:16
 * @Version 1.0
 */
@WebServlet("/servletDirectionA")
public class ServletDirectionA extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("servletDirectionA 已经接收用户请求");

        // http://localhost:8080/forwardandredirection/servletDirectionA?key=value
        String value = req.getParameter("key");
        System.out.println("ServletForwardA get key = " + value);

        //设置响应重定向
//        resp.setStatus(302);
//        resp.setHeader("Location", "servletDirectionB");

        // 这一行代码就完成了上面两行代码的作用，既设置了响应头，也设置了响应路径
        // resp.sendRedirect("servletDirectionB");     可以
        // resp.sendRedirect("forward.html");          可以
        // resp.sendRedirect("WEB-INF/forword.html");  不可以是 WEB-INF 受保护的资源
        // resp.sendRedirect("https://www.baidu.com"); 可以

        resp.sendRedirect("servletDirectionB");


        /**
         * 1.重定向是通过 HttpServletResponse 实现的
         * 2.响应重定向是在服务器提示下，客户端的行为
         * 3.客户端的地址栏是变化的，客户端至少产生了两次请求（可能产生多次请求）
         * 4.请求产生多次，后端就会产生多个 request && reponse 对象，此时请求中的参数不能进行传递
         * 5.目标资源可以是视图资源
         * 6.目标资源不可以是 WEB-INF 下的资源
         * 7.目标资源可以是外部资源
         */

    }
}
