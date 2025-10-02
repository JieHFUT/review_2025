package com.jiehfut.hssmwebmvcpractice.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * 拦截器（功能其实和过滤器是一样的）
 * ===== 权限检查 ===== 日志记录 ===== 数据共享 ======
 * web 阶段的过滤器是过滤 servlet 请求的
 * spring 阶段的拦截器是拦截控制器方法的
 *
 * preHandle => controller => postHandle => afterCompletion
 */
@Component // 还需要配置拦截哪些请求
public class HandlerInterceptorForThisProject1 implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("001-控制器方法执行之前 ====> 拦截器 preHandle 放行");
        // 放行：chain.doFilter(request, response)
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("001-控制器方法执行之后 ====> 拦截器 postHandle");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("001-控制器方法响应之前 ====> 拦截器 afterCompletion");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
