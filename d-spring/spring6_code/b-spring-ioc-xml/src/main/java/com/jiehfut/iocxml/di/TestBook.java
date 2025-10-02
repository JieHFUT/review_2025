package com.jiehfut.iocxml.di;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ClassName: TestBook
 * Package: com.jiehfut.iocxml.di
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/11/28 23:32
 * @Version 1.0
 */
public class TestBook {
    @Test
    public void testSetterToDI(){

        ApplicationContext context = new ClassPathXmlApplicationContext("bean-di.xml");
        Book book = (Book) context.getBean("book", Book.class);
        System.out.println(book.toString());
        /**
         * setter 进行依赖注入
         * 无参数构造器...
         * Book{bname='前端开发', author='王仁杰'}
         */

    }

    @Test
    public void testConstructorToDI(){

        ApplicationContext context = new ClassPathXmlApplicationContext("bean-di.xml");
        Book book = (Book) context.getBean("bookCon", Book.class);
        System.out.println(book.toString());
        /**
         * Constructor 基于有参构造器进行依赖注入
         * 有参数构造器...
         * Book{bname='后端开发', author='大傻春'}
         */
    }


    // 如果两种 BeanDefinition（定义信息）都加载到 Ioc 容器中，那么无论是哪种注入方法，都会执行无参构造

    /**
     *
     * 2024-11-28 23:53:01 506 [main] DEBUG org.springframework.context.support.ClassPathXmlApplicationContext - Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@39ad977d
     * 2024-11-28 23:53:01 587 [main] DEBUG org.springframework.beans.factory.xml.XmlBeanDefinitionReader - Loaded 2 bean definitions from class path resource [bean-di.xml]
     * 2024-11-28 23:53:01 600 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'book'
     * 无参数构造器...
     * 2024-11-28 23:53:01 623 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'bookCon'
     * 2024-11-28 23:53:01 630 [main] WARN org.springframework.core.LocalVariableTableParameterNameDiscoverer - Using deprecated '-debug' fallback for parameter name resolution. Compile the affected code with '-parameters' instead or avoid its introspection: com.jiehfut.iocxml.di.Book
     * 有参数构造器...
     * Book{bname='前端开发', author='王仁杰'}
     *
     *
     * 2024-11-28 23:52:39 475 [main] DEBUG org.springframework.context.support.ClassPathXmlApplicationContext - Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@39ad977d
     * 2024-11-28 23:52:39 555 [main] DEBUG org.springframework.beans.factory.xml.XmlBeanDefinitionReader - Loaded 2 bean definitions from class path resource [bean-di.xml]
     * 2024-11-28 23:52:39 570 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'book'
     * 无参数构造器...
     * 2024-11-28 23:52:39 593 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'bookCon'
     * 2024-11-28 23:52:39 600 [main] WARN org.springframework.core.LocalVariableTableParameterNameDiscoverer - Using deprecated '-debug' fallback for parameter name resolution. Compile the affected code with '-parameters' instead or avoid its introspection: com.jiehfut.iocxml.di.Book
     * 有参数构造器...
     * Book{bname='后端开发', author='大傻春'}
     */
}
