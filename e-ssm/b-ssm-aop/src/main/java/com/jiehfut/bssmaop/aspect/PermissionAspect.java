package com.jiehfut.bssmaop.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;


// 权限切面类
@Order(1) // 多切面执行顺序
@Aspect // 表示是一个切面类
@Component // 表示在 ioc 容器中进行管理
public class PermissionAspect {

    // 1.前置 @Before(value = "切入点表达式配置切入点")、
    @Before(value = "execution(public int com.jiehfut.bssmaop.calculator.impl.CalculatorImpl.add(int, int ))")
    public void beforeMethod(JoinPoint joinPoint) {
        // 使用 joinPoint 可以获得增强方法的相关信息
        String name = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("Permission-->前置通知，方法名称是：" + name + "，参数是：" + Arrays.toString(args));
    }

    @After(value = "pointCut()")
    public void afterMethod(JoinPoint joinPoint) {
        // 使用 joinPoint 可以获得增强方法的相关信息
        String name = joinPoint.getSignature().getName();
        System.out.println("Permission-->后置通知，方法名称是：" + name);
    }

    // 3.返回 @AfterReturning、增强方法可以得到目标方法的返回值（声明返回值的接受类）：returning = "result"
    @AfterReturning(value = "execution(* com.jiehfut.bssmaop.calculator.impl.CalculatorImpl.*(..))", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        // 使用 joinPoint 可以获得增强方法的相关信息
        String name = joinPoint.getSignature().getName();
        System.out.println("Permission-->返回通知，方法名称是：" + name + "，目标方法的返回结果是：" + result);
    }

    // 4.异常 @AfterThrowing、如果你的目标方法执行过程中出现了异常就会执行，否则不会执行（声明异常的接收类）
    @AfterThrowing(value = "execution(* com.jiehfut.bssmaop.calculator.impl.CalculatorImpl.*(..))", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        // 使用 joinPoint 可以获得增强方法的相关信息
        String name = joinPoint.getSignature().getName();
        System.out.println("Permission-->异常通知，方法名称是：" + name + "，异常信息是：" + ex);
    }

/*    // 5.环绕 @Around()、针对 try-catch
    @Around(value = "execution(* com.jiehfut.bssmaop.calculator.impl.CalculatorImpl.*(..))")
    public Object afterAround(ProceedingJoinPoint joinPoint) {
        // 使用 joinPoint 可以获得增强方法的相关信息
        String name = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String argsStr = Arrays.toString(args);
        Object result = null;
        try{
            System.out.println("环绕通知（Permission） => 目标方法之前执行");
            // 通过 ProceedingJoinPoint 调用目标方法
            result = joinPoint.proceed();
            System.out.println("环绕通知（Permission） => 目标方法返回值之后执行");
        } catch (Throwable throwable) {
            System.out.println("环绕通知（Permission） => 目标方法出现异常执行");
        } finally {
            System.out.println("环绕通知（Permission） => 目标方法执行完毕后执行");
        }
        return result;
    }*/

    // 重用切入点表达式
    @Pointcut(value = "execution(* com.jiehfut.bssmaop.calculator.impl.CalculatorImpl.*(..))")
    public void pointCut() {}


}
