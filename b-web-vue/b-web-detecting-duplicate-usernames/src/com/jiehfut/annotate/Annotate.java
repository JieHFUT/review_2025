package com.jiehfut.annotate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


/**
 * 如果在 @WebServlet("/annotate") 中 java.lang.IllegalStateException: 启动子级时出错
 * 1.没有添加 /
 * 2.有相同的路径名
 * 3.web.xml 和 @WebServlet 配置了同一个 servlet 情况
 *
 * @WebServlet 中属性，只有一个 value 可以省略不写，写成 @WebServlet("/annotate")
 *
 *
 * value 和 url-pattern 互为别名，给其中一个赋值就相当于给两个赋值
 * @WebServlet(value = {"/annotate1"，"/annotate2"，"/annotate3"})
 * @WebServlet(value = "/annotate")
 *
 * @WebServlet(url-pattern = {"/annotate1"，"/annotate2"，"/annotate3"})
 * @WebServlet(url-pattern = "/annotate")
 *
 */
@WebServlet("/annotate")
public class Annotate extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("annotate");
    }
}
