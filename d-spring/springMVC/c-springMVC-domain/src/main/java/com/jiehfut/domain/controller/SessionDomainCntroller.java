package com.jiehfut.domain.controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SessionDomainCntroller {


    @RequestMapping("/testSessionServletAPI")
    public String testSessionServletAPI(HttpSession session) {
        session.setAttribute("sessionDomainKeya", "sessionDomainValuea");
        return "success";
    }




}
