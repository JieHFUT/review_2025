package com.jiehfut.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * ClassName: TestInterceptor
 * Package: com.jiehfut.interceptors
 * Description:
 *   第二个拦截器
 *   针对 "/testInterceptor" 控制器的拦截器
 *   需要在 springMVC 中进行配置才能使用
 * @Author jieHFUT
 * @Create 2024/12/8 15:46
 * @Version 1.0
 */

@Component
public class MoreInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 针对控制器方法执行之前执行，返回值为是否进行拦截
        System.out.println("MoreInterceptor ===> preHandle...");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 在控制器方法执行之后执行
        System.out.println("MoreInterceptor ===> postHandle...");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 在渲染视图完毕之后执行
        System.out.println("MoreInterceptor ===> afterCompletion...");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }



}
