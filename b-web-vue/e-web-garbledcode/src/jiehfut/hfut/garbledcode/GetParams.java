package jiehfut.hfut.garbledcode;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * ClassName: GetParams
 * Package: jie.hfut.garbledcode
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/11/23 0:36
 * @Version 1.0
 */


/**
 * GET 请求参数乱码问题
 */
@WebServlet("/getParams")
public class GetParams extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取参数并且输出
        System.out.println("username = " + req.getParameter("username"));
        // 如果 html 设置发送过来的数据是 GBK 编码，输出则会乱码

        // tomcat-conf-server.xml-<Connector> 设置使用的字符集
        /*
        <Connector port="8080" protocol="HTTP/1.1"
        connectionTimeout="20000"
        redirectPort="8443"
        maxParameterCount="1000"
        URIEncoding="UTF-8"/>

        GET /garbledcode/getParams?username=%CE%D2%B5%C4 HTTP/1.1
        改变的是对于请求行的 URI 这一部分的解码的字符集 （/garbledcode/getParams?username=%CE%D2%B5%C4）
        后端这一块对其进行解析的时候就会使用设置的编码格式对 URI 进行解码
        />*/
    }
}
