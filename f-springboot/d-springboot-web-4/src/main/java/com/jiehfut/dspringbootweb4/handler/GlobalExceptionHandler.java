package com.jiehfut.dspringbootweb4.handler;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@ControllerAdvice // 全局错误处理
public class GlobalExceptionHandler {


    public String handlerException(Exception e) {
        // 统一捕捉所有错误信息
        return "exception 统一处理，原因：" + e.getMessage();
    }

    /**
     * 如果 springmvc 处理不了的异常会转交给 springboot 的 "/error"
     * 协商处理
     */


}
