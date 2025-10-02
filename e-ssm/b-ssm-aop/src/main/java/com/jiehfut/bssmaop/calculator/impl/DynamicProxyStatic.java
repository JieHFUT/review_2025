package com.jiehfut.bssmaop.calculator.impl;

import org.springframework.context.annotation.Profile;

import java.lang.reflect.Proxy;

/**
 * 动态代理类对象的封装
 *
 */
public class DynamicProxyStatic {

    public static Object getProxyInstance(Object target) {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    return null;
                }
        );
    }
}
