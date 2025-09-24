package jiehfut.hfut.cookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * ClassName: ServletA
 * Package: jie.hfut.cookieandsession
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/11/24 16:14
 * @Version 1.0
 */

@WebServlet("/servleta")
public class ServletA extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.创建 cookie 对象
        Cookie cookie1 = new Cookie("keya", "valuea");
        // 将 cookie1 设置成为持久化 cookie
        cookie1.setMaxAge(60*3);//该 cookie 保存时间是 180s
        Cookie cookie2 = new Cookie("keyb", "valueb");
        // 设置 cookie 的提交路径
        cookie1.setPath("/servletb");

        // 2.将 cookie 对象放在 response 对象中
        resp.addCookie(cookie1);
        resp.addCookie(cookie2);
    }
}
