package com.jiehfut.bssmaop.log;

import java.lang.reflect.Method;
import java.util.Arrays;


/**
 * 抽取日志信息，工具类
 * 在动态代理过程中，对日志的输出进行一个封装
 */

public class LogUtils {

    public static void logStart(Method method, Object[] args) {
        System.out.println("[动态代理][日志] "+method.getName()+"，参数："+ Arrays.toString(args));
    }

    public static void logResult(String methodName, Object result) {
        System.out.println("[动态代理][日志] " + methodName +"，结果："+ result);
    }
    
    public static void logException(String methodName, Throwable e) {
        System.out.println("[动态代理][日志] "+ methodName +"，异常："+ e.getMessage());
    }

    public static void logEnd(String methodName) {
        System.out.println("[动态代理][日志] "+ methodName +"，方法执行完毕");
    }
    

}
