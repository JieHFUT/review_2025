package com.jiehfut.aop.annotationaop;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestCalcuator {

    @Test
    public void testAopAdd(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-aop-anno.xml");
        Calculator calculator = context.getBean(Calculator.class);
        calculator.add(12, 23);

        /**
         * 环绕通知 => 目标方法之前执行
         * Logger-->前置通知，方法名称是：add，参数是：[12, 23]
         * 方法内部 result = 35
         * Logger-->返回通知，方法名称是：add，目标方法的返回结果是：35
         * Logger-->后置通知，方法名称是：add
         * 环绕通知 => 目标方法返回值之后执行
         * 环绕通知 => 目标方法执行完毕后执行
         */
    }



}
