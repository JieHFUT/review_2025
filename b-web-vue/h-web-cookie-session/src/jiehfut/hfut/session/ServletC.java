package jiehfut.hfut.session;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * ClassName: ServletC
 * Package: jie.hfut.session
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/11/24 17:16
 * @Version 1.0
 */

@WebServlet("/servletc")
public class ServletC extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.接收请求中的 username 参数
        String username = req.getParameter("username");

        // 2.获得 session 对象
        /**
         * 获得 session 对象的过程
         * 判断请求中有没有一个特殊的 cookie，即为 JSESSIONID="573B5A7439B207E7115DB105BB8FE614"
         * 1.有这样的 cookie 对象
         *     根据 JSESSIONID 去找对应的 session 对象
         *     （1）在服务器端找到了这个 session 对象：返回之前的这个 session 对象
         *     （2）在服务器端没有找到 session 对象：创建一个新的 session 对象返回，并且自动向 response 对象中存放一个 JSESSIONID 的 cookie
         * 2.没有这样的 cookie 对象
         *     创建一个新的 session 对象返回，并且自动向 response 对象中存放一个 JSESSIONID 的 cookie
         */
        HttpSession session = req.getSession();
        // 判断这个 session 对象是不是新的
        boolean isNew = session.isNew();
        System.out.println("此 session 对象ID = " + session.getId());
        System.out.println("此 session 对象 isNew = " + session.isNew());

        // 3.将 username 存如入 session
        session.setAttribute("username", username);

        // 4.向客户端返回响应
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.write("成功");


        /**
         * 如上述描述，默认参数为 true，等同于 HttpSession session = req.getSession(true);
         * HttpSession session = req.getSession();
         *
         * 加 true 的话，如果服务器中存在对应的 Session 就返回该 Session 对象，否则就新建立一个会话
         * HttpSession session = req.getSession(true);
         *
         * 加 false 的话，如果服务器中存在对应的 Session 就返回该 Session 对象，否则就新返回 null
         * HttpSession session = req.getSession(false);
         */
    }
}
