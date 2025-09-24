package jiehfut.hfut.schedule.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * ClassName: SysScheduleNewController
 * Package: jie.hfut.schedule.controller
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/11/24 13:37
 * @Version 1.0
 */

@WebServlet("/schedule/*")
public class SysScheduleController extends BaseController {
    // 视为已经实现了 service 方法
    // 对上面的方法进行封装
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("add");
    }
    protected void find(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("find");
    }
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("update");
    }
    protected void remove(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("remove");
    }
}
