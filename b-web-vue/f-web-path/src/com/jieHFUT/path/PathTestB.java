package com.jieHFUT.path;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * ClassName: PathTestB
 * Package: com.jieHFUT.path
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/11/23 11:31
 * @Version 1.0
 */
@WebServlet("/pathTestB")
public class PathTestB extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("PathTestB");
    }
}
