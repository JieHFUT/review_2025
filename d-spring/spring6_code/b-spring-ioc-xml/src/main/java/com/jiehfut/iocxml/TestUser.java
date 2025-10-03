package com.jiehfut.iocxml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ClassName: TestUser
 * Package: com.jiehfut.iocxml
 * Description: 创建对象的日志信息

 - Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@14a50707
 - Loaded 1 bean definitions from class path resource [bean.xml]
   在加载配置文件
 - Creating shared instance of singleton bean 'user'
   在创建 User 对象（单例）

 * @Author jieHFUT
 * @Create 2024/11/28 22:17
 * @Version 1.0
 */
public class TestUser {
    public static void main(String[] args) {
        // 1.获取配置信息
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        // 2.根据 id（唯一标识） 获取 bean
        User userById = (User) context.getBean("user");
        // 2.根据类型获取 bean
        User userByClass = context.getBean(User.class);
        // 2.根据 id && 类型获取 bean
        User userByIdAndClass = context.getBean("user", User.class);

        // 上面的三种获取 bean 的方式获取的 user 对象地址一样
        // 根据类型获取 bean 要求只能有一个实例的 bean：否则报错 NoUniqueBeanDefinitionException
    }
}
