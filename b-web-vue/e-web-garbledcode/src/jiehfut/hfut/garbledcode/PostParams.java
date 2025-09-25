package jiehfut.hfut.garbledcode;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * ClassName: PostParams
 * Package: jie.hfut
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/11/23 10:34
 * @Version 1.0
 */

/**
 * POST 请求参数乱码问题
 */
@WebServlet("/postParams")
public class PostParams extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取参数并且输出
        // System.out.println("username = " + req.getParameter("username"));
        // 如果 html 设置发送过来的数据是 GBK 编码，输出则会乱码

        // 和 GET 不一样，就算修改 tomcat-conf-server.xml-<Connector>
        // 设置使用的字符集和 HTML 保持一致都是 GBK 还是会出现乱码
        // 因为 tomcat-conf-server.xml-<Connector> 只会修改 URI 后面的参数使用的字符集
        // 而 POST 请求的数据是放在请求体中的
        //
        // tomcat10 默认是使用 UTF-8 对请求体进行解码
        // 但是如果设置 HTML 中客户端提交数据的编码方式为 GBK 格式,那么输出解析的请求体就会出现乱码
        // 就需要设置请求体的解码字符集

        req.setCharacterEncoding("UTF-8");
        System.out.println("username = " + req.getParameter("username"));



    }
}
