package com.jiehfut.forwardingandredirection.forward;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/servletForwardA")
public class ServletForwardA extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletForwardA 执行了");


        // http://localhost:8080/forwardandredirection/servletForwardA?key=value
        String value = req.getParameter("key");
        System.out.println("ServletForwardA get key = " + value);

        // 将请求转发给 ServletForwardB
        // 获得请求转发器 => 可以转发到其他 servlet 或者转发到某一个静态资源
        // RequestDispatcher requestDispatcher1 = req.getRequestDispatcher("servletForwardB"); // 可以
        // RequestDispatcher requestDispatcher2 = req.getRequestDispatcher("forward.html");    // 可以
        // RequestDispatcher requestDispatcher3 = req.getRequestDispatcher("WEB-INF/forward.html");    // 可以
        // RequestDispatcher requestDispatcher4 = req.getRequestDispatcher("https://www.baidu.com");   // 不可以


        // 下面两行是进行请求转发
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("servletForwardB");
        // 让请求转发器做出请求转发动作（将 request && response 对象转发给另外一个 servlet）
        requestDispatcher.forward(req, resp);


        /**
         * 很重要 !!!
         * 1.请求转发是通过 HttpServletRequest 对象实现的
         * 2.请求转发是服务器内部行为，对客户端是屏蔽的
         * 3.客户端只产生了一次请求，服务端只产生了一对 resquest && response 对象
         * 4.客户端的地址栏是不变的
         * 5.请求的参数是可以继续传递的
         * 6.目标资源可以是静态资源，也可以是动态资源
         * 7.目标资源可以是 WEB-INF下受到保护的资源，也可以是 WEB-INF 下的资源的唯一访问方式
         * 8.目标资源不可以是 web资源（非当前项目里面的资源，如：www.baidu.com）=> 404
         */


    }
}
