package com.jiehfut.domain.controller;


import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationDomainController {

    /**
     * 应用域的数据共享
     * （列表，修改回显，错误信息提示）一般使用请求域共享信息
     * （保存用户的登陆状态）一般使用会话域共享信息
     * （工程全局使用的参数）一般使用应用域共享信息
     * @return
     */
    @RequestMapping("/testApplication")
    public String testApplication(HttpSession session) {
        ServletContext application = session.getServletContext();
        application.setAttribute("applicationDomainKeya", "applicationDomainValuea");
        return "success";
    }



}
