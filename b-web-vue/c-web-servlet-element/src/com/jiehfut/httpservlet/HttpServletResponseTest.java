package com.jiehfut.httpservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


// 0003
@WebServlet("/httpServletResponse")
public class HttpServletResponseTest extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置响应行相关的 API       HTTP/1.1    200/404/500/405...
        resp.setStatus(200);






        String content = "<h1>Hello World</h1>";
        int length = content.getBytes().length;

        // 设置响应行相关的 API
        resp.setHeader("keya", "valuea");
        // 告诉客户端响应体里的数据是什么类型
        resp.setContentType("text/html");
        // 告诉客户端里面有多少数据
        resp.setContentLength(length);









        // 设置响应体里面内容的 API
        // 获得一个向响应体中输入文本字符输出流
        PrintWriter writer = resp.getWriter();
        writer.write(content);

        // 获得一个向响应体中输入二进制信息的字节输出流
        ServletOutputStream outputStream = resp.getOutputStream();



        /**
         * 在 ServletResponse 中
         * 获取一个向响应体中输入二进制的字节流的对象（char int... String）
         * ServletOutputStream getOutputStream() throws IOException;
         *
         * 获得一个向响应体中输入文本字符输出流（char int... String, Object）
         * PrintWriter getWriter() throws IOException;
         */







    }
}
