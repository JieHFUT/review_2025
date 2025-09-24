package jiehfut.schedule.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jiehfut.schedule.common.Result;
import jiehfut.schedule.common.ResultCodeEnum;
import jiehfut.schedule.pojo.SysUser;
import jiehfut.schedule.service.SysUserService;
import jiehfut.schedule.service.impl.SysUserServiceImpl;
import jiehfut.schedule.util.MD5Util;
import jiehfut.schedule.util.WebUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: SysUserController
 * Package: jiehfut.schedule.controller
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
        // String username = req.getParameter("username");
        // String userPwd = req.getParameter("userPwd");
        // 客户端发送的是 JSON 串 {username: "zhangsanx", userPwd: "123456"}
        // 传统的 req.getParameter() 是不能从 JSON 串中获取数据的，需要将 JSON 串转换为 User 对象


/*      BufferedReader reader = req.getReader();
        StringBuffer buffer = new StringBuffer();
        String line = reader.readLine();
        while (line != null) {
            buffer.append(line);
            line = reader.readLine();
        }
        // 这样就会将 JSON 串拼接为一个完成的 JSON 字符串
        ObjectMapper mapper = new ObjectMapper();
        SysUser user = mapper.readValue(buffer.toString(), SysUser.class);
        上面的这些步骤可以使用工具类中的方法*/
        SysUser registUser = WebUtil.readJson(req, SysUser.class);


        // 2.调用服务层的方法，完成注册功能
        int rows = userService.regist(registUser);
        // 3.根据注册业务的返回结果（注册成功/注册失败），进行页面跳转
        Result result = Result.ok(null);
        if (rows < 1) {
            result = Result.build(null, ResultCodeEnum.USERNAME_USED);
        }
        WebUtil.writeJson(resp, result);
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
        // String username = req.getParameter("username");
        // String userPwd = req.getParameter("userPwd");
        SysUser sysUser = WebUtil.readJson(req, SysUser.class);

        // 2.调用 service 层方法查询用户信息
        SysUser loginUser = userService.findByUsername(sysUser.getUsername());
        Result result = null;
        if (null == loginUser) {
            // 没有找到该用户名对应的用户信息
            // resp.sendRedirect("/loginUsernameError.html");
            // 返回的是用户名有误的信息
            result = Result.build(null, ResultCodeEnum.USERNAME_ERROR);

        // 3.找到了对应的用户信息，判断用户密码是否正确
        } else if (!MD5Util.encrypt(sysUser.getUserPwd()).equals(loginUser.getUserPwd())){
            // 用户密码错误
            // resp.sendRedirect("/loginUserPwdError.html");
            result = Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
        } else {
            // 4.登录成功，跳转相关页面
            // 登陆成功之后，将登陆成功的用户信息放进 session 域
            // 后期使用 token 进行登陆判断
            // HttpSession session = req.getSession();
            // session.setAttribute("sysUser", loginUser);
            // resp.sendRedirect("/showSchedule.html");

            // 登陆成功，将用户的 UID 和用户的 username 放在 result 中
            Map data = new HashMap();
            // data.put("uid", loginUser.getUid());
            // data.put("username", loginUser.getUsername());

            // 或者将 loginUser 的 password 置空后，将 loginUser 对象放在 map 中
            loginUser.setUserPwd(null);
            data.put("loginUser", loginUser);

            result = Result.ok(data);
        }

        WebUtil.writeJson(resp, result);
    }


    /**
     * 用于检查在注册时候，接收注册的用户名，校验用户名是否被占用的接口
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void checkUsernameUsed(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.接收用户名
        String username = req.getParameter("username");
        // 2.调用服务层的业务处理方法查询该用户名是否有对应的用户
        SysUser sysUser = userService.findByUsername(username);

        // 3.构造 result 响应对象
        // 在这里响应一个成功的状态码：ok()方法中自己添加了一个 sucess 的枚举
        Result result = Result.ok(null);

        if (null != sysUser) {
            // 有该用户 => 响应已被占用
            result = Result.build(null, ResultCodeEnum.USERNAME_USED);
        }

        // 4.利用工具类 WebUtil 将 result 对象封装称为 JSON 字符串，并且将 JSON 字符串发送给客户端
        WebUtil.writeJson(resp, result);
    }
}
