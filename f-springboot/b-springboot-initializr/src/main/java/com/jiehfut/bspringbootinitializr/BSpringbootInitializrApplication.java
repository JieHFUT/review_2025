package com.jiehfut.bspringbootinitializr;

import com.jiehfut.bspringbootinitializr.bean.Conf;
import com.jiehfut.bspringbootinitializr.bean.Conf1;
import com.jiehfut.bspringbootinitializr.bean.yaml.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // 主程序
                        // 默认只会扫描主程序所在的包及其子包
public class BSpringbootInitializrApplication {

    public static void main(String[] args) {

        // Java10 局部变量类型的自动推断
        var ioc = SpringApplication.run(BSpringbootInitializrApplication.class, args);

        String[] beanDefinitionNames = ioc.getBeanDefinitionNames();
        // 遍历组件：dispatcherServlet beanNameViewResolver  characterEncodingFilter...
        for (String beanDefinitionName : beanDefinitionNames) {
            // System.out.println(beanDefinitionName);
        }

        // 获取刚才注册的用户组件，默认是单实例的
        Object user = ioc.getBean("user");
        System.out.println(user);
        Object lisi = ioc.getBean("lisi");
        System.out.println(lisi);


        Conf conf = ioc.getBean(Conf.class);
        System.out.println(conf);  // Conf(id=10, name=张三, age=20)

        Conf1 conf1 = ioc.getBean(Conf1.class);
        System.out.println(conf1); // Conf1(id=20, name=李四, age=30)

        /**
         * application.properties 配置 Person 类
         *
         */
        Person person = ioc.getBean(Person.class);
        System.out.println(person);
        // Person(name=zhangsan, age=18, birthDay=Tue Oct 12 12:12:12 CST 2010, like=true,
        // child=Child(name=lisi, age=12, birthDay=null, text=[abc, qww]),
        // dogs=[Dog(name=xiaohuang, age=2), Dog(name=xoiaobai, age=3)],
        // cats={c1=Cat(name=xiaorong, age=2), c2=Cat(name=xiaoruan, age=2)})
        


    }

    /**
     * springboot 到底是如何工作的？
     * 1.例如导入 starter-web 开发场景
     *     该场景启动器为我们导入了需要的各种场景
     *     spring-boot-starter
     *     spring-boot-starter-json
     *     spring-boot-starter-tomcat
     *     spring-web
     *     spring-webmvc
     * 实际上每个场景启动器都引入了一个核心启动器 spring-boot-starter
     * 在这个核心启动器中导入了 spring-boot-autoconfigure
     *                     spring-boot
     *                     spring-boot-starter-logging
     *                     jakarta.annotation-api
     *                     spring-core
     * 在 spring-boot-autoconfigure 中写明了全场景的所有配置信息
     * 只要这个包下的所有类都能生效，那么相当于 SpringBoot 官方写好的整合功能就会生效了
     * 但是 springboot 默认扫描不到 spring-boot-autoconfigure 下写好的所有配置类（这些配置类给我们做了整合操作）
     *
     *
     *
     * 在主程序中 @SpringBootApplication
     * 这个注解由以下注解组成：
     *      @SpringBootConfiguration 就是声明是一个配置类的
     *      @EnableAutoConfiguration springboot 开启自动配置的核心
     *      @ComponentScan
     * 注解约束了 springboot 默认只能扫描足迹主程序下的包及其下面的子包，扫描不到 spring-boot-autoconfigure 包中官方写好的配置类
     *
     *
     *
     * @EnableAutoConfiguration
     * 该注解中有 @Import(AutoConfigurationImportSelector.class) 这是进行批量导入
     * springboot 启动会默认加载 142 个配置类，META-INF/spring/org.springframework.boot.autoconfigure.imports
     * 这个注解的作用就是在项目启动的时候，利用 @Import(***.class) 注解
     * 批量导入机制把 spring-boot-autoconfigure 包下的142个***AutoConfigration 类导入进来（自动配置）
     * 但是不是这 142 个类（自动配置类）都注入容器中，每一个自动配置类都有条件注解，即为按需配置
     * 每一个自动配置类 ***AutoConfigration 类
     *      1.按需加载进来的给容器中使用 @Bean 放置了一对组件
     *      2.每个自动配置类，都可能有注解 @EnableConfigurationProperties({ServerProperties.class})
     *        用来把配置文件中的指定前缀的属性封装b b到 ***Properties 属性类中，如此是服务器配置都是以 server 开头的配置，进行属性绑定
     * 说明这些自动配置类 ***AutoConfigration 类里面的一些核心属性都来自于 ***Properties 属性类中
     * ***Properties 属性类即是和配置文件进行绑定的，所以可以通过更改配置文件信息来修改配置信息
     * 2.主程序 @SpringBootApplication
     * 3.完成业务代码，无需关心各种整合
     */









}
