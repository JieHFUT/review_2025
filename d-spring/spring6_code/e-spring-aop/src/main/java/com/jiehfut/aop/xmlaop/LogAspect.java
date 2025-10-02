package com.jiehfut.aop.xmlaop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

// 切面类
@Component // 表示在 ioc 容器中进行管理
public class LogAspect {

    // 1.前置
    public void beforeMethod(JoinPoint joinPoint) {
        // 使用 joinPoint 可以获得增强方法的相关信息
        String name = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("Logger-->前置通知，方法名称是：" + name + "，参数是：" + Arrays.toString(args));
    }


    // 2.后置
    public void afterMethod(JoinPoint joinPoint) {
        // 使用 joinPoint 可以获得增强方法的相关信息
        String name = joinPoint.getSignature().getName();
        System.out.println("Logger-->后置通知，方法名称是：" + name);
    }


    // 3.返回
    public void afterReturningMethod(JoinPoint joinPoint, Object result) {
        // 使用 joinPoint 可以获得增强方法的相关信息
        String name = joinPoint.getSignature().getName();
        System.out.println("Logger-->返回通知，方法名称是：" + name + "，目标方法的返回结果是：" + result);
    }


    // 4.异常
    public void afterThrowingMethod(JoinPoint joinPoint, Exception ex) {
        // 使用 joinPoint 可以获得增强方法的相关信息
        String name = joinPoint.getSignature().getName();
        System.out.println("Logger-->异常通知，方法名称是：" + name + "，异常信息是：" + ex);
    }


    // 5.环绕
    public Object afterAroundMethod(ProceedingJoinPoint joinPoint) {
        // 使用 joinPoint 可以获得增强方法的相关信息
        String name = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String argsStr = Arrays.toString(args);
        Object result = null;
        try{
            System.out.println("环绕通知 => 目标方法之前执行");
            // 通过 ProceedingJoinPoint 调用目标方法
            result = joinPoint.proceed();

            System.out.println("环绕通知 => 目标方法返回值之后执行");
        } catch (Throwable throwable) {
            System.out.println("环绕通知 => 目标方法出现异常执行");
        } finally {
            System.out.println("环绕通知 => 目标方法执行完毕后执行");
        }
        return result;
    }


    // 重用切入点表达式
    @Pointcut(value = "execution(* com.jiehfut.aop.xmlaop.CalculatorImpl.*(..))")
    public void pointCut() {}

    // 切面的优先级 @Order() 来控制




}
