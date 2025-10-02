package com.jiehfut.iocxml.special;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpecial {
    @Test
    public void testEmpOuter(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-special.xml");
        // 员工对象（外部 bean 引入）
        Emp emp = (Emp) context.getBean("emp", Emp.class);
        emp.work(); // tom is 22 years old and go to work in 安保部门
    }

    @Test
    public void testEmpInner(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-special.xml");
        // 员工对象（外部 bean 引入）
        Emp emp = (Emp) context.getBean("emp2", Emp.class);
        emp.work(); // jerry is 20 years old and go to work in 财务部门
    }

    @Test
    public void testJiLian(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-special.xml");
        // 员工对象（级联方式引入）
        Emp emp = (Emp) context.getBean("emp3", Emp.class);
        emp.work(); // duck is 17 years old and go to work in 测试部门
    }

}
