package com.jiehfut.bssmaop.calculator.impl;


import com.jiehfut.bssmaop.log.LogUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

// 动态代理
public class DynamicProxy {

    // 需要代理的目标对象
    private Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    // 创建一个方法，返回动态的代理对象
    public Object getProxy() {
        /**
         * Proxy.newProxyInstance()
         * 其中的三个参数：newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)
         * 1.类加载器，加载动态生成代理类的类加载器（目标对象）
         * 2.目标对象实现的所有的接口类型的数组
         * 3.设置代理对象实现目标方法的过程
         */
        ClassLoader classLoader = target.getClass().getClassLoader();
        Class<?>[] interfaces = target.getClass().getInterfaces();

        InvocationHandler invocationHandler = new InvocationHandler() {
            /**
             *
             * @param proxy 代理对象
             * @param method 需要重写目标对象的方法
             * @param args method 方法里面的参数
             * @return
             * @throws Throwable
             */
            Object result = null;
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                try {
                    // todo：log.LogUtils 记录日志开始
                    LogUtils.logStart(method, args);
                    // System.out.println("[动态代理][日志] "+method.getName()+"，参数："+ Arrays.toString(args));
                    //////////************/////////////
                    result = method.invoke(target, args);
                    //////////************/////////////
                    System.out.println("[动态代理][日志] "+method.getName()+"，结果："+ result);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("[动态代理][日志] "+method.getName()+"，异常："+e.getMessage());
                } finally {
                    System.out.println("[动态代理][日志] "+method.getName()+"，方法执行完毕");
                }
                return result;
            }
        };
        

        return Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
    }
}

