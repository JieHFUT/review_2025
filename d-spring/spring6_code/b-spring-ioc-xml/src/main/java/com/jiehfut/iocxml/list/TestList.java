package com.jiehfut.iocxml.list;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestList {
    @Test
    public void testListDept(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-list.xml");
        Dept dept = context.getBean("dept", Dept.class);
        // 员工对象的 list
        dept.info();

        /**
         部门名称：项目管理部...
         Emp{dept=null, ename='zhangsan', age=19}
         Emp{dept=null, ename='lisi', age=29}

         */
    }
}
