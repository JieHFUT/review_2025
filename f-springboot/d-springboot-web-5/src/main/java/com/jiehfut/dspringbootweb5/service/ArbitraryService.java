package com.jiehfut.dspringbootweb5.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 实现在任意位置获取使用该方法的请求对象
 * Arbitrary 任意的
 */
@Service
public class ArbitraryService {

    public void getResquestAndResponse() {
        // 当前请求的路径
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // 获取请求和相应
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        // 获取请求中的数据
        String uri = request.getRequestURI();

    }



}
