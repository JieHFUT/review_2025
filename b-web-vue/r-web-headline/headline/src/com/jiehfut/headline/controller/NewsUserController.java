package com.jiehfut.headline.controller;

import com.jiehfut.headline.common.Result;
import com.jiehfut.headline.common.ResultCodeEnum;
import com.jiehfut.headline.pojo.NewsUser;
import com.jiehfut.headline.service.NewsUserService;
import com.jiehfut.headline.service.impl.NewsUserServiceImpl;
import com.jiehfut.headline.util.JwtHelper;
import com.jiehfut.headline.util.MD5Util;
import com.jiehfut.headline.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: NewsHeadlineController
 * Package: com.jiehfut.headline.controller
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/12/22 20:19
 * @Version 1.0
 */

@WebServlet("/user/*")
public class NewsUserController extends BaseController {

    private NewsUserService newsUserService = new NewsUserServiceImpl();
    /**
     * 处理登录表单提交的业务接口的实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收用户名和密码 JSON 串
        NewsUser paramUser = WebUtil.readJson(req, NewsUser.class);
        // 调用服务层方法查找用户信息
        NewsUser loginUser = newsUserService.findByUsername(paramUser.getUsername());
        Result result = null;
        if (loginUser != null) {
            // 验证密码
            boolean isPasswordRight = MD5Util.encrypt(paramUser.getUserPwd()).equalsIgnoreCase(loginUser.getUserPwd());
            if (isPasswordRight) {
                // 密码正确响应 token
                Integer uid = loginUser.getUid();
                String token = JwtHelper.createToken(uid.longValue());
                Map data = new HashMap();
                data.put("token", token);
                result = Result.ok(data);
            } else {
                result = Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
            }
        } else {
            // 数据库里没有该用户信息
            result = Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }

        WebUtil.writeJson(resp, result);
    }


    /**
     * 根据用户 token 口令，获取用户信息，将用户 uid && 用户名发送给前端
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getUserInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求头中的 token
        String token = req.getHeader("token");
        // 校验 token，通过响应 200 & 用户信息，没有通过响应 504
        Result result = Result.build(null, ResultCodeEnum.NOTLOGIN);
        if (null != token && !"".equals(token)) {
            if (!JwtHelper.isExpiration(token)) {
                // 没有过期
                Integer uid = JwtHelper.getUserId(token).intValue();
                NewsUser user = newsUserService.findByUid(uid);
                if (null != user) {
                    // 数据库中匹配到了用户信息
                    Map data = new HashMap();
                    user.setUserPwd(null);
                    data.put("user", user);
                    result = Result.ok(data);
                }
            }
        }
        WebUtil.writeJson(resp, result);
    }


    /**
     * 用户注册的时候，填写完用户名，立刻发送请求检查用户名是不是被占用 post
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void checkUserName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取用户名
        String username = req.getParameter("username");
        // 检验用户名是否被占用
        NewsUser newsUser = newsUserService.findByUsername(username);
        Result result = Result.ok(null);
        if (null != newsUser) {
            result = Result.build(null, ResultCodeEnum.USERNAME_USED);
        }
        WebUtil.writeJson(resp, result);
    }


    /**
     * 用户填写完注册信息后，点击提交按钮，提交用户账号和密码等信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收请求中的 JSON 信息
        NewsUser newsUser = WebUtil.readJson(req, NewsUser.class);
        // 将用户数据存入数据库
        Result result = Result.ok(null);
        try {
            Integer rows = newsUserService.registUser(newsUser);
        } catch (Exception e) {
            result = Result.build(null, ResultCodeEnum.USERNAME_USED);
            System.out.println(e.getMessage());
        } finally {
            WebUtil.writeJson(resp, result);
        }
    }












}
