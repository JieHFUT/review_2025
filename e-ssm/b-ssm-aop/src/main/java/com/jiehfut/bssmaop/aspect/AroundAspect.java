package com.jiehfut.bssmaop.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class AroundAspect {
    /**
     * 环绕通知：一般拎出来单写
     * 1.返回值： Object
     * 2.参数：ProceedingJoinPoint joinPoint
     *
     * 如果出现异常，环绕通知将异常处理了，外层的代理就不会出现异常，走返回通知
     * 很多种情况下，应该把异常抛出去
     */

    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs(); // 获取目标方法的参数
        // 环绕前置通知
        System.out.println("环绕 - 前置通知... 参数 = " + Arrays.toString(args));
        try {
            // 继续执行目标方法，类似于反射的 invoke() 方法
            // 接收参数之后，可以修改参数，然后导致修改目标方法的返回值
            Object proceed = joinPoint.proceed(args);
            // 环绕返回通知
            System.out.println("环绕 - 返回通知... 返回 = " + proceed);
        } catch (Exception e) {
            System.out.println("环绕 - 异常通知... 异常 = " + e.getMessage());
            throw e; // 外层代理继续感知异常
        } finally {
            // 环绕后置通知
            System.out.println("环绕 - 后置通知...");
        }
        // 修改返回值
        return null;
    }

}
