package com.jiehfut.iocxml.array;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestArrray {
    @Test
    public void testArrayEmp(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-array.xml");
        Emp emp = context.getBean("emp", Emp.class);
        emp.work();
        System.out.println(emp);
        /**
         zhangsan is 18 years old and go to work in IT开发部门
         Emp{dept=com.jiehfut.iocxml.array.Dept@4d4d8fcf, ename='zhangsan', age=18, hobbies=[吃饭, 睡觉, 代码]}
         */
    }
}
