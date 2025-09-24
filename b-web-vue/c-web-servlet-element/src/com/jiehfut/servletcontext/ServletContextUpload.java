package com.jiehfut.servletcontext;


import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/servletContextUpload")
public class ServletContextUpload extends HttpServlet {

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 由 servletContext 配置信息给我们提供一个路径
        ServletContext servletContext = getServletContext();

        // 向 upload 文件夹中写入一个文件
        // getRealPath：获得一个指向项目的部署位置下的某个目录的磁盘的真实路径的 API

        String path = servletContext.getRealPath("upload");
        System.out.println("path = " + path);
        // path = E:\code\web_code\out\artifacts\c_web_servlet_element_war_exploded\\upload
        // FileOutputStream fos = new FileOutputStream(path + "\\test.txt");


        // 获取项目部署的上下文路径，也就是项目的访问路径（edit Configrations... 中配置的上下文路径）
        String contextPath = servletContext.getContextPath();
        System.out.println("contextPath = " + contextPath);





    }
}
