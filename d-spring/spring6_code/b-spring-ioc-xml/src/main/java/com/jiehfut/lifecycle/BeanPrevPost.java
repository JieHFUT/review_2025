package com.jiehfut.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;


/**
 * 配置后：todo: 后置处理器在整个 IoC 容器中对所有的 bean 对象生效
 * 实现接口 BeanPostProcessor，重写后置处理器方法
 */

public class BeanPrevPost implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("3.后置处理器在 bean 初始化之前执行...");
        System.out.println(beanName + "::" + bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("5.后置处理器在 bean 初始化之后执行...");
        System.out.println(beanName + "::" + bean);
        return bean;
    }
}
