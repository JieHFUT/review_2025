package jiehfut.hfut.session;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * ClassName: ServletD
 * Package: jie.hfut.session
 * Description:
 * 模仿用户携带 cookie（JSESSIONID）发送请求的情况
 * @Author jieHFUT
 * @Create 2024/11/24 17:16
 * @Version 1.0
 */

@WebServlet("/servletd")
public class ServletD extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获得 session 对象
        HttpSession session = req.getSession();
        System.out.println("此 session 对象ID = " + session.getId());
        System.out.println("此 session 对象 isNew = " + session.isNew());

        // 2.读取 session 中的用户名
        String username = (String) session.getAttribute("username");
        //
        System.out.println("servletd get username = " + username);

    }
}
