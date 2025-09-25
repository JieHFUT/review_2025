package jiehfut.hfut.garbledcode;

/**
 * ClassName: ResponseGarbled
 * Package: jie.hfut.garbledcode
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/11/23 11:02
 * @Version 1.0
 */

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 响应体乱码问题
 */
@WebServlet("/respondseGarbled")
public class ResponseGarbled extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 下面这两行一个设置服务器编码方式，一个告诉客户端解码方式（实际情况选择一个设置即可）

        // 为了设置响应体使用 UTF-8 来进行编码
        resp.setCharacterEncoding("UTF-8");

        // 来设置 ContentType 响应头来告诉客户端采用 UTF-8 进行解码
        resp.setContentType("text/html;charset=UTF-8");


        // 向客户端响应一些数据 write 向响应体输出字符的一个输出流
        resp.getWriter().write("你好，world!");
        // 如果不设置响应体的字符集，客户端输出乱码，需要提前设置响应体编码格式

        /**
         * 响应行
         * 响应行
         * 响应体：Tomcat 中默认的字符集是 UTF-8
         *        但是浏览器不知道使用说明解码，就会使用操作系统的语言环境使用 GBK 对响应体中的数据进行解码
         *
         *
         *
         * 后端可以设置响应的编码方式去适应前端 resp.setCharacterEncoding("GBK");
         * 但是具体前端是什么来访问是不确定的（如国外访问），所以这种方式不是很好
         * 解决思路：
         *      1.后端适应前端（不推荐）
         *          resp.setCharacterEncoding("GBK");
         *      2.指定客户端使用什么字符集进行解码（设置 Content-Type 响应头，推荐）
         *          resp.setContentType("text/html;charset=UTF-8");
         *
         * 注意：如果只选择方法二，要先明确响应体的编码（Tomcat8 版本可能是默认响应为 GBK 字符集）
         * 然后再设置 Content-Type 响应头
         */



    }
}
