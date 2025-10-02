package com.jiehfut.hssmwebmvcpractice.advice;


import com.jiehfut.hssmwebmvcpractice.common.R;
import com.jiehfut.hssmwebmvcpractice.exception.BizException;
import com.jiehfut.hssmwebmvcpractice.exception.BizExceptionEnume;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 对控制层利用切面机制进行增强 - 从而统一处理异常行为
 * @ResponseBody + @ControllerAdvice = @RestControllerAdvice
 *
 * 1.优先本类中异常处理
 * 2.再者优先全局异常处理
 * 3.如果本类中和全局异常处理类中都没有处理某异常的能力，springboot 提供了默认的兜底机制（自适应处理-浏览器响应白页）
 *
 * 前端关心异常状态，后端关心业务流程处理
 * 推荐：后端只要编写正确的业务逻辑，如果出现业务逻辑，后端通过抛出异常的方式提前中断业务逻辑，让前端感知异常
 */

// @ResponseBody
// @ControllerAdvice // 告诉 springmvc 这是全局处理异常的类
@RestControllerAdvice // 合并注解
public class GlobalExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    public R exceptionHandler(Exception e) {
        return R.error("GlobalExceptionHandler：" + e.getMessage());
    }


    @ExceptionHandler(value = BizException.class)
    public R bizExceptionHandler(BizException e) {
        Integer code = e.getCode();
        String msg = e.getMsg();
        return R.error(code, "GlobalExceptionHandler：" + msg);
    }

    /**
     * 方法参数没有通过校验异常
     * 被全局异常处理器捕捉到以后，不会执行控制层方法，直接响应异常 JSON 信息
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        // 说明校验错误信息，拿到所有错误信息
        Map<String, String> errorMap = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            // 1.拿到出错的属性名
            String fieldName = fieldError.getField();
            // 2.拿到默认出错消息
            String errorMessage = fieldError.getDefaultMessage();
            errorMap.put(fieldName, errorMessage);
        }
        return R.error(BizExceptionEnume.EMPLOYEE_DATA_VALID, errorMap);
    }



}
