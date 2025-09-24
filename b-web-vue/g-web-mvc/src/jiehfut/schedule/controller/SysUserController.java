package jiehfut.hfut.schedule.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jiehfut.hfut.schedule.pojo.SysUser;
import jiehfut.hfut.schedule.service.SysUserService;
import jiehfut.hfut.schedule.service.impl.SysUserServiceImpl;
import jiehfut.hfut.schedule.util.MD5Util;

import java.io.IOException;

/**
 * ClassName: SysUserController
 * Package: jie.hfut.schedule.controller
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/11/24 12:48
 * @Version 1.0
 */

@WebServlet("/user/*")
public class SysUserController extends BaseController {

    private SysUserService userService = new SysUserServiceImpl();
    /**
     * 接收用户进行注册请求的业务逻辑处理方法（接口-前后端业务处理对接接口）
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.接收客户端提交的参数（username userPwd）
        String username = req.getParameter("username");
        String userPwd = req.getParameter("userPwd");

        // 2.调用服务层的方法，完成注册功能
            // 将参数放入一个 SysUser 对象中，在调用 regist 方法的时候传入
        SysUser sysUser = new SysUser(null, username, userPwd);
        int rows = userService.regist(sysUser);
        // 3.根据注册业务的返回结果（注册成功/注册失败），进行页面跳转
        if (rows > 0) {
            // 重定向到注册成功页面
            resp.sendRedirect("/registSuccess.html");
        } else {
            // 重定向到注册失败页面
            resp.sendRedirect("/registFail.html");
        }
    }


    /**
     * 接收用户登录请求时候，完成登录业务的方法（登录业务接口）
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.接收用户名和密码
        String username = req.getParameter("username");
        String userPwd = req.getParameter("userPwd");
        // 2.调用 service 层方法查询用户信息
        SysUser loginUser = userService.findByUsername(username);
        if (null == loginUser) {
            // 没有找到该用户名对应的用户信息
            resp.sendRedirect("/loginUsernameError.html");

        // 找到了对应的用户信息，判断用户密码是否正确
        } else if (!MD5Util.encrypt(userPwd).equals(loginUser.getUserPwd())){
            // 用户密码错误
            resp.sendRedirect("/loginUserPwdError.html");
        } else {
            // 4.登录成功，跳转相关页面
            resp.sendRedirect("/showSchedule.html");
        }
    }










}
