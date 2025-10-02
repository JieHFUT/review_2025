package com.jiehfut.controller;

import com.jiehfut.exception.SelfException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * ClassName: TestExceptionAnnotation
 * Package: com.jiehfut.controller
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/12/8 18:48
 * @Version 1.0
 */
@ControllerAdvice
public class TestExceptionAnnotation {

    // 若出现下面这些异常就会通过异常的视图显示信息，形参中的 exception 就是我们出现的异常
    @ExceptionHandler({SelfException.class, NullPointerException.class})
    public String handleSelfException(Exception selfException, Model model) {
        // 将报错的信息共享在请求域中
        model.addAttribute("msg", selfException.getMessage());
        return "error";
    }




}
