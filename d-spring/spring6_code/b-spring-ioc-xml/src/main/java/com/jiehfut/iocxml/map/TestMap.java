package com.jiehfut.iocxml.map;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMap {

    @Test
    public void testMapStudent(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-map.xml");
        Student student = (Student) context.getBean("student",Student.class);

        System.out.println(student.run());
        student.teacherMap(); // 输出 map 中的所有 teacher

        /**
         Student{sid='217456', sname='xiaoliang'}
         Teacher{tid='76', tname='zhansgan'}
         Teacher{tid='36', tname='lisi'}
         */

    }


    @Test
    public void testRefStudent(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-di-ref.xml");
        Student student = (Student) context.getBean("student",Student.class);

        System.out.println(student.run());
        student.teacherMap();
        System.out.println(student.getLessonList());

        /**
         Student{sid='01', sname='xiaomin'}
         Teacher{tid='0001', tname='zhangsan'}
         Teacher{tid='0002', tname='lisi'}
         [Lesson{lname='JVM虚拟机'}, Lesson{lname='java并发编程的魅力'}]
         */

    }
}
