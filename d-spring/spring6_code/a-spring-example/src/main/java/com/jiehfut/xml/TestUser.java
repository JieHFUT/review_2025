package com.jiehfut.xml;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUser {

    // 创建 Logger 对象
    private Logger logger = LoggerFactory.getLogger(TestUser.class);


    @Test
    public void testUserObject(){
        // 1.加载 spring 配置文件，进行对象创建（），创建的对象是 ioc 容器
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

        System.out.println("***** id = " + context.getId());
        System.out.println("***** applicationName = " + context.getApplicationName());
        System.out.println("***** displayName = " + context.getDisplayName());
        System.out.println("***** startupDate = " + context.getStartupDate());
        System.out.println("***** parent = " + context.getParent());

        // 2.获取对象的创建
        User user = (User) context.getBean("user");
        System.out.println("user = " + user);
        // 3.使用对象调用方法进行测试
        user.add();



        /*
            如何使用反射创建的对象：
            1.加载 .xml 配置文件
            2.对 .xml 文件进行解析，如 dom4j 解析 xml 文档
            3.获得 .xml 文件中的属性，得到 bean 标签值 id 属性值和 class 属性值
            4.使用反射根据（无参）类的全路径创建对象 Class.forName(class属性值)
            5.创建好的对象被统一管理到一个 map 中 （DefaultListableBeanFactory 类中有一个 map 属性
                                             private final Map<String, BeanDefinition> beanDefinitionMap;）
                                             其中 String 就是自己设定xml配置中唯一的标识
                                             beanDefinition 是指类的描述信息（bean对象）
            6.Spring容器加载到Bean类时 , 会把这个类的描述信息, 以包名加类名的方式存到 beanDefinitionMap 中
                                     （是否单例、Bean 类名、scope 作用域、isPrimary是否单例、是否懒加载 isLazyInit）
              Map<String,BeanDefinition> , 其中 String是Key, 默认是类名首字母小写
              BeanDefinition, 存的是类的定义(描述信息) , 我们通常叫 BeanDefinition 接口为 : bean的定义对象。
        */

        // 测试手动写日志，可以将其打印到日志中
        logger.info("logger 调用成功！！！！！！！！！！！！！");



        /**
         * 2024-12-26 08:46:13 224 [main] DEBUG org.springframework.context.support.ClassPathXmlApplicationContext - Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@239b0f9d
         * 2024-12-26 08:46:13 380 [main] DEBUG org.springframework.beans.factory.xml.XmlBeanDefinitionReader - Loaded 1 bean definitions from class path resource [bean.xml]
         * 2024-12-26 08:46:13 411 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'user'
         * User 的无参构造方法...
         * ***** id = org.springframework.context.support.ClassPathXmlApplicationContext@3a62c01e
         * ***** applicationName =
         * ***** displayName = org.springframework.context.support.ClassPathXmlApplicationContext@3a62c01e
         * ***** startupDate = 1735175030375
         * ***** parent = null
         * user = com.jiehfut.xml.User@4201a617
         * add...
         * 2024-12-26 08:46:13 446 [main] INFO com.jiehfut.xml.TestUser - logger 调用成功！！！！！！！！！！！！！
         */
    }


    // 通过反射创建对象（回顾反射）
    @Test
    public void testUserObjectByReflection() throws Exception {
        // 获取类 Class 对象
        Class clazz = Class.forName("com.jiehfut.xml.User");
        // 调用方法创建对象
        User user1 = (User) clazz.newInstance();
        // 或者通过构造方法
        User user2 = (User) clazz.getDeclaredConstructor().newInstance();
    }


}
