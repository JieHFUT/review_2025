package com.jiehfut.bssmaop.calculator.impl;

import org.springframework.context.annotation.Profile;

import java.lang.reflect.Proxy;

/**
 * 动态代理类对象的封装
 * 动态代理就是 Proxy.newProxyInstance(1, 2, 3)
 * 1 是类加载器
 * 2 是接口数组
 * 3.代理的具体实现
 */
public class DynamicProxyStatic {

    public static Object getProxyInstance(Object target) {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                // lamba 表达式，表示代理对象的具体实现流程，这里没有写
                (proxy, method, args) -> {
                    // .....
                    // 这里面就可以写日志，就是 invoke() 方法里面的内容
                    return null;
                }
        );
    }
}
