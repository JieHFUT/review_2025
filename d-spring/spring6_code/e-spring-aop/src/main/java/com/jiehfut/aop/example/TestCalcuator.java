package com.jiehfut.aop.example;

import org.junit.jupiter.api.Test;

public class TestCalcuator {

    @Test
    // 原始方式，不通过代理
    public void testCalculatorLogImplTest() {
        CalculatorLogImpl calculatorLog = new CalculatorLogImpl();
        calculatorLog.add(1, 2);
        /**
         * [日志] add 方法开始了，参数是：1,2
         * 方法内部 result = 3
         * [日志] add 方法结束了，结果是：3
         */
    }


    
    @Test
    // 静态代理，需要代理的类型是固定的 CalculatorImpl 类型
    public void testCalculatorStaticProxy(){
        CalculatorStaticProxy calculatorStaticProxy = new CalculatorStaticProxy(new CalculatorImpl());
        calculatorStaticProxy.add(1,2);
        /**
         * [日志] add 方法开始了，参数是：1,2
         * 方法内部 result = 3
         * [日志] add 方法结束了，结果是：3
         */
    }



    @Test
    // 动态代理，需要代理的对象是 Object 类型
    public void testProxyFactory(){
        // 创建动态代理类对象
        ProxyFactory proxyFactory = new ProxyFactory(new CalculatorImpl());
        Calculator proxy = (Calculator) proxyFactory.getProxy();
        proxy.add(1,2);

        /**
         * [动态代理][日志] add，参数：[1, 2]
         * 方法内部 result = 3
         * [动态代理][日志] add，结果：3
         * [动态代理][日志] add，方法执行完毕
         */
    }



}
