package com.jiehfut.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 1.创建一个 javaweb 项目，同时将 tomcat 添加为当前项目的依赖
 * 2.重写 service(HttpServletRequest req, HttpServletResponse resp) 方法
 * 3.在 service 方法中，定义业务代码
 * 4.在 web.xml 文件中去配置 servlet 对应的请求映射路径
 *
 */
public class UserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1.从request对象中获取请求报文中的任何信息（username 参数）
        String username = request.getParameter("username");//根据参数名获取参数值，无论参数是在URL中，还是在请求体中

        // 2.处理业务的代码
        String ret = "<h1>YES</h1>";
        if (username.equals("jieHFUT")) {
            ret = "<h1>NO<h1>";
        }

        // 3.将要响应的代码放进 reponse 中
        // 设置响应头中的 Content-Type
        response.setHeader("Content-Type", "text/html");
        response.setCharacterEncoding("UTF-8");

        // response.setContentType("text/html");
        PrintWriter writer = response.getWriter(); // 该方法返回的是一个向响应体中打印字符串的 打印流
        writer.write(ret);

        // 4.请求路径
    }


    /**
     * 1.servlet-api.jar 导入问题
     *   servlet httpservlet genericservlet 这几个类是 Tomcat 中自带的 => lib.servlet-api.jar
     *   servlet-api 编码的时候需要，运行的时候，在服务器的环境中，由服务软件（Tomcat）提供
     *   因此，我们的 javaweb 项目中，打包/构建的时候无需携带 servlet-api 的 jar 包
     *
     * 2.Content-Type 响应头的问题
     *   MIME 类型响应头 => 媒体类型，文件类型，响应的数据类型
     *   MIME 类型用于告诉客户端响应的数据是什么类型的数据，客户端以此类型决定用什么方式解析响应体
     *   在 tomcat 的 conf/web.xml 文件中，记录了几乎所有的文件类型对应的 MIME 类型
     *   如果客户端需要上传文件，请求中也需要有 Content-Type 响应头，告诉服务器你上传的文件类型是什么
     *
     * text/html ： HTML格式
     * text/plain ：纯文本格式
     * text/xml ：  XML格式
     * image/gif ：gif图片格式
     * image/jpeg ：jpg图片格式
     * image/png：png图片格式
     *
     * application/xhtml+xml ：XHTML格式
     * application/xml     ： XML数据格式
     * application/atom+xml  ：Atom XML聚合格式
     * application/json    ： JSON数据格式
     * application/pdf       ：pdf格式
     * application/msword  ： Word文档格式
     * application/octet-stream ： 二进制流数据（如常见的文件下载）
     *
     * 下面是表单发动的默认是 key-value 模式
     * application/x-www-form-urlencoded ： <form encType=””>中默认的 encType，form表单数据被编码为
     *                                      key/value格式发送到服务器（表单默认的提交数据的格式）
     *
     *   如果没有这个响应头，客户端就会将其当成 text/html文件处理
     */
}
