package com.jiehfut.iocxml.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ClassName: TestUserDao
 * Package: com.jiehfut.iocxml.bean
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/11/28 23:13
 * @Version 1.0
 */
public class TestUserDao {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        // 根据类型获取接口对应的 bean
        UserDao userdao = context.getBean(UserDao.class);
        System.out.println("userdao = " + userdao);
        userdao.run();
        /**
         userdao = com.jiehfut.iocxml.bean.UserDaoImpl@5a85c92
         run()...

         很明显，如果一个接口只有一个实现类 UserDaoImpl，接口类型可以通过接口的类型获取 bean
         但是如果有两个类 UserDaoImpl && UserDaoImpl2 实现了同一个接口，那么这个接口就不可以通过接口的类型来获取 bean 对象，否则报错 NoUniqueBeanDefinitionException

         */
    }
}
