package com.jieHFUT.path;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * ClassName: PathTest
 * Package: com.jieHFUT.path
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/11/23 11:29
 * @Version 1.0
 */
@WebServlet("/pathTestA")
public class PathTestA extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("PathTestA");




        // TODO: 重定向资源到 B
        /**
         1.相对路径写法【和前端的相对路径规则一致】
            A 请求重定向 B ====> resp.sendRedirect("pathTestB");
            http://localhost:8080/path/pathTestA ==>（客户端得到响应 302  Location:pathTestB）==> http://localhost:8080/path/pathTestB => 200

            若是将 A 的请求路径设置为 @WebServlet("/A/B/C/pathTestA") && B 不变
            http://localhost:8080/path/A/B/C/pathTestA ==>（客户端得到响应 302  Location:pathTestB）==> http://localhost:8080/path//A/B/C/pathTestB => 404
            此时就需要 resp.sendRedirect("../../../pathTestB");


         2.绝对路径写法【和前端的相对路径规则一致】
            http://localhost:8080/  在 8080 后面加绝对路径
         */

        // 相对路径（以 ./ ../ 为出发点）
        resp.sendRedirect("pathTestB");
        // 绝对路径（以 / 为出发点）
        resp.sendRedirect("/path/pathTestB");

        // 想要动态处理上下文变化的办法
        ServletContext servletContext = req.getServletContext();
        String path = servletContext.getContextPath(); // 如 "/path"
        resp.sendRedirect(path + "/pathTestB");











        // TODO: 请求转发资源到 B
        /**
         1.相对路径写法【和前端的相对路径规则一致】

         2.绝对路径写法
            请求转发的绝对路径是不需要添加上下文的
            请求转发的 / 代表项目上下文之后的那个点，不在需要写项目上下文

         */

        // 相对路径（以 ./ ../ 为出发点）
        req.getRequestDispatcher("pathTestB").forward(req, resp);
        // 绝对路径（）
        req.getRequestDispatcher("/pathTestB").forward(req, resp);










        // TODO: 如何做到不要项目上下文
        /**
         Edit Configurations-Deployment-Application context 中设置项目上下文只为 "/"

         */


    }
}
