package com.jiehfut.assmioc.factory;

import com.jiehfut.assmioc.bean.Car;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;


@Component
/**
 * 如果制造某些对象比较复杂
 * 利用工厂方法进行创建
 */
public class CheryFactory implements FactoryBean<Car> {

    @Override
    /**
     * ioc 容器就会调用该方法进行创建对象
     */
    public Car getObject() throws Exception {
        System.out.println("chery factory 正在制造汽车..."); // 什么时候使用什么时候创建对象
        return new Car("chery", "small", "red");
    }
    

    @Override
    /**
     * 说明造的东西的类型
     */
    public Class<?> getObjectType() {
        return Car.class;
    }


    @Override
    /**
     * 是否是单例，默认是单例的
     * return true：是单例的
     * return false：不是单例的
     */
    public boolean isSingleton() {
        return true;
    }
}
