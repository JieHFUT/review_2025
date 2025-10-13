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
            3.获得 .xml 文件中的属性，得到 bean 标签值 id 属性值和 class 属性值，等等等
              这些属性被称为 bean 的定义信息
            4.使用反射根据（无参）类的全路径创建对象 Class.forName(class属性值)

            5.创建好的对象被统一管理到一个 map 中 （DefaultListableBeanFactory 类中有一个 map 属性
                                             private final Map<String, BeanDefinition> beanDefinitionMap;）
                                             其中 String 就是自己设定xml配置中唯一的标识
                                             beanDefinition 是指类的描述信息（bean对象）
            6.Spring容器加载到Bean类时 , 会把这个类的描述信息, 以包名加类名的方式存到 beanDefinitionMap 中
                                     （是否单例、Bean 类名、scope 作用域、isPrimary是否单例、是否懒加载 isLazyInit）
              Map<String,BeanDefinition> , 其中 String是 Key, 默认是类名首字母小写
              BeanDefinition, 存的是类的定义(描述信息) , 我们通常叫 BeanDefinition 接口为 : bean的定义对象。






              IoC 容器负责帮你创建对象、管理它们的生命周期、并组装它们之间的依赖关系

              以Spring框架为例，最核心的IoC容器接口是ApplicationContext（应用上下文）。它实际上是一个接口，具体的实现类有很多，
              比如 AnnotationConfigApplicationContext、ClassPathXmlApplicationContext等。这些具体的实现类就是IoC容器的具体体现。

              所以，当我们说“容器”时，通常指的是ApplicationContext（或其父接口BeanFactory）的一个实例。
              这个实例内部维护了一个Bean定义（BeanDefinition）的注册表，并且负责根据这些定义创建Bean实例，解决依赖关系，以及管理Bean的整个生命周期
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


    /**
     * 1.xxx.xml 文件中有<bean>xxx<bean/>标签
     * 2.由 BeanDefinitionReader 读取加载（有不同的实现类）xml 文件，将其解析为 BeanDefinition 接口的一个实现类对象
     * 3.将该定义信息注册到 beanFactory 接口的一个实现类工厂中
     *   DefaultListableBeanFactory 类中有一个 map 属性
     *   private final Map<String, BeanDefinition> beanDefinitionMap;）
     *   其中 String 就是自己设定xml配置中唯一的标识
     *   beanDefinition 是指类的描述信息（bean对象）
     * 4.当客户端调用 beanFactory 接口的 getBean()方法时，如果Bean是单例且尚未初始化，则容器会实例化Bean，并注入其依赖
     *
     *
     *
     *
     *
     * 1. BeanFactory
     * BeanFactory是Spring框架中最基本的IoC容器，它提供了IoC容器最基本的功能。它使用延迟加载策略，只有在客户端请求一个Bean时才会对Bean进行初始化。
     *
     * 主要作用：
     * 负责配置、创建和管理Bean。
     *
     * 提供了基础的依赖注入支持。
     *
     * 装配流程（以XmlBeanFactory为例，已过时，但有助于理解）：
     * 加载配置文件：读取XML配置文件。
     *
     * 解析Bean定义：将XML中的<bean>元素解析成BeanDefinition对象，并注册到BeanFactory中。
     *
     * 依赖注入：当客户端调用getBean()方法时，如果Bean是单例且尚未初始化，则容器会实例化Bean，并注入其依赖。
     *
     * 代码示例（已过时，仅用于说明）：
     * java
     * BeanFactory factory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
     * MyBean myBean = (MyBean) factory.getBean("myBean");
     * 流程详解：
     * 第一步：通过ClassPathResource加载XML配置文件。
     *
     * 第二步：XmlBeanFactory初始化，内部会创建一个DefaultListableBeanFactory，然后使用XmlBeanDefinitionReader读取配置资源，将每个Bean定义解析为BeanDefinition并注册到DefaultListableBeanFactory中。
     *
     * 第三步：当调用getBean时，容器会先检查Bean是否已经初始化（对于单例），如果没有，则实例化Bean，并填充属性（依赖注入）。
     *
     * 2. ApplicationContext
     * ApplicationContext是BeanFactory的子接口，它提供了更多的企业级功能。它在启动时就预加载所有的单例Bean，属于立即加载策略。此外，它还提供了消息资源处理、事件发布、应用层上下文等功能。
     *
     * 主要作用：
     * 继承BeanFactory的功能，并在此基础上扩展。
     *
     * 提供文本解析、AOP、事务管理等功能。
     *
     * 装配流程（以ClassPathXmlApplicationContext为例）：
     * 加载配置文件：读取XML配置文件。
     *
     * 解析Bean定义：将XML中的Bean定义解析成BeanDefinition，并注册到容器中。
     *
     * BeanFactory后处理：调用BeanFactoryPostProcessor对Bean定义进行修改。
     *
     * Bean后处理：调用BeanPostProcessor对Bean实例进行增强。
     *
     * 初始化单例Bean：容器启动时立即初始化所有非懒加载的单例Bean。
     *
     * 代码示例：
     * java
     * ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
     * MyBean myBean = context.getBean(MyBean.class);
     * 流程详解：
     * 第一步：创建ClassPathXmlApplicationContext实例，指定配置文件。
     *
     * 第二步：调用refresh()方法（在父类AbstractApplicationContext中）启动容器。
     *
     * 在这个方法中，会创建BeanFactory（默认是DefaultListableBeanFactory），并加载Bean定义。
     *
     * 然后调用BeanFactoryPostProcessor（例如PropertySourcesPlaceholderConfigurer）对Bean定义进行修改，比如替换占位符。
     *
     * 接着注册BeanPostProcessor，这些处理器会在Bean实例化前后执行。
     *
     * 最后初始化所有非懒加载的单例Bean，包括实例化、填充属性、初始化（调用初始化方法、应用BeanPostProcessor的后处理）等。
     *
     * 3. ClassPathXmlApplicationContext
     * ClassPathXmlApplicationContext是ApplicationContext的一个具体实现，它从类路径下加载XML配置文件。
     *
     * 主要作用：
     * 从类路径中加载XML配置文件，创建ApplicationContext。
     *
     * 装配流程：
     * 构造函数：在构造函数中设置配置文件路径，并调用refresh()方法。
     *
     * 刷新上下文：refresh()方法中完成以下步骤：
     *
     * 准备刷新：初始化启动时间、激活状态等。
     *
     * 获取BeanFactory：创建DefaultListableBeanFactory。
     *
     * 加载Bean定义：使用XmlBeanDefinitionReader读取XML文件，解析并注册Bean定义。
     *
     * 后处理BeanFactory：调用所有注册的BeanFactoryPostProcessor。
     *
     * 注册Bean后处理器：注册所有BeanPostProcessor。
     *
     * 初始化消息源、事件广播器等。
     *
     * 初始化所有非懒加载的单例Bean。
     *
     * 完成刷新：发布上下文刷新事件。
     *
     * 代码示例：
     * java
     * ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
     * 流程详解：
     * 首先，在创建ClassPathXmlApplicationContext时，会调用其构造函数，构造函数中会调用refresh()方法。
     *
     * refresh()方法是在AbstractApplicationContext中定义的，它包含了完整的容器启动流程。
     *
     * 创建BeanFactory：obtainFreshBeanFactory()方法会创建BeanFactory（DefaultListableBeanFactory）并加载Bean定义。
     *
     * 然后调用invokeBeanFactoryPostProcessors(beanFactory)执行BeanFactoryPostProcessor，这些处理器可以修改Bean定义。
     *
     * 接着调用registerBeanPostProcessors(beanFactory)注册BeanPostProcessor，这些处理器在Bean初始化前后被调用。
     *
     * 然后调用onRefresh()方法（子类可以重写），初始化一些特殊的Bean。
     *
     * 最后调用finishBeanFactoryInitialization(beanFactory)初始化所有非懒加载的单例Bean。
     *
     * 总结
     * BeanFactory：基础容器，延迟加载。使用getBean()时才会初始化Bean。
     *
     * ApplicationContext：扩展了BeanFactory，提供了更多功能，立即加载（在启动时初始化所有单例Bean）。
     *
     * ClassPathXmlApplicationContext：ApplicationContext的具体实现，从类路径加载XML配置。
     *
     * 在Spring应用中，通常使用ApplicationContext，因为它提供了更全面的功能。而BeanFactory通常在资源受限的环境下使用，比如移动设备。
     */


}
