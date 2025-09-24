package jiehfut.hfut.cookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * ClassName: ServletB
 * Package: jie.hfut.cookieandsession
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/11/24 16:14
 * @Version 1.0
 */

@WebServlet("/servletb")
public class ServletB extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取请求中携带的 cookie 对象
        Cookie[] cookies = req.getCookies();
        // 2.请求中的多个 cookie 对象进入该数组
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName() + " = " + cookie.getValue());
            }
        }
    }

}
