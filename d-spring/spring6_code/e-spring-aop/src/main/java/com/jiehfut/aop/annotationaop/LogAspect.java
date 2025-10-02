package com.jiehfut.aop.annotationaop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.junit.jupiter.api.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

// 切面类
@Aspect // 表示是一个切面类
@Component // 表示在 ioc 容器中进行管理
public class LogAspect {
    // 1.设置切入点和通知类型：value：切入点表达式配置切入点：即对哪个目标方法进行前置通知
    // "execution(public int com.jiehfut.aop.annotationaop.CalculatorImpl.add(int, int ))"
    // "访问修饰符 增强方法返回类型 增强方法所在类全路径.方法名称（方法参数）"

    // 前置 @Before(value = "切入点表达式配置切入点")、
    // 返回 @AfterReturning、
    // 异常 @AfterThrowing、
    // 后置 @After()、
    // 环绕 @Around()、







    // 1.前置 @Before(value = "切入点表达式配置切入点")、
    @Before(value = "execution(public int com.jiehfut.aop.annotationaop.CalculatorImpl.add(int, int ))")
    public void beforeMethod(JoinPoint joinPoint) {
        // 使用 joinPoint 可以获得增强方法的相关信息
        String name = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("Logger-->前置通知，方法名称是：" + name + "，参数是：" + Arrays.toString(args));
    }


    // 2.后置 @After()、
    // @After(value = "pointCut()")
    // @After(value = "com.jiehfut.aop.annotationaop.LogAspect.pointCut()")
    // @After(value = "execution(* com.jiehfut.aop.annotationaop.CalculatorImpl.*(..))")
    //
    @After(value = "pointCut()")
    public void afterMethod(JoinPoint joinPoint) {
        // 使用 joinPoint 可以获得增强方法的相关信息
        String name = joinPoint.getSignature().getName();
        System.out.println("Logger-->后置通知，方法名称是：" + name);
    }


    // 3.返回 @AfterReturning、增强方法可以得到目标方法的返回值：returning = "result"
    @AfterReturning(value = "execution(* com.jiehfut.aop.annotationaop.CalculatorImpl.*(..))", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        // 使用 joinPoint 可以获得增强方法的相关信息
        String name = joinPoint.getSignature().getName();
        System.out.println("Logger-->返回通知，方法名称是：" + name + "，目标方法的返回结果是：" + result);
    }


    // 4.异常 @AfterThrowing、如果你的目标方法执行过程中出现了异常就会执行，否则不会执行
    @AfterThrowing(value = "execution(* com.jiehfut.aop.annotationaop.CalculatorImpl.*(..))", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        // 使用 joinPoint 可以获得增强方法的相关信息
        String name = joinPoint.getSignature().getName();
        System.out.println("Logger-->异常通知，方法名称是：" + name + "，异常信息是：" + ex);
    }


    // 5.环绕 @Around()、针对 try-catch
    @Around(value = "execution(* com.jiehfut.aop.annotationaop.CalculatorImpl.*(..))")
    public Object afterAround(ProceedingJoinPoint joinPoint) {
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
    @Pointcut(value = "execution(* com.jiehfut.aop.annotationaop.CalculatorImpl.*(..))")
    public void pointCut() {}

    // 切面的优先级 @Order() 来控制




}
