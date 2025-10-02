package com.jiehfut.assmexample;

import com.jiehfut.assmioc.bean.*;
import com.jiehfut.assmioc.controller.UserController;
import com.jiehfut.assmioc.datasource.DataSources;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 主入口类别
 * 程序启动的入口
 */

@SpringBootApplication
public class ASsmIocApplication {

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext ioc = SpringApplication.run(ASsmIocApplication.class, args);
        System.out.println("ioc = " + ioc);

        System.out.println("=====ioc 容器创建完成========");
        Dog dog = ioc.getBean(Dog.class);
        System.out.println("dog = " + dog);


        Dog dog1 = ioc.getBean(Dog.class);
        System.out.println("dog1 = " + dog1);
        Dog dog2 = ioc.getBean(Dog.class);
        System.out.println("dog2 = " + dog2);

        /**
         * 正在创建 Dog 组件...
         * 2024-12-25T09:28:19.295+08:00  INFO 25692 --- [a-ssm-example] [           main] c.j.assmexample.ASsmExampleApplication   : Started ASsmExampleApplication in 0.814 seconds (process running for 1.291)
         * ioc = org.springframework.context.annotation.AnnotationConfigApplicationContext@5ce8d869, started on Wed Dec 25 09:28:18 CST 2024
         * =====ioc 容器创建完成========
         * dog = Dog(name=dog, age=0)
         * dog1 = Dog(name=dog, age=0)
         * dog2 = Dog(name=dog, age=0)
         *
         * 组件术语框架的一些底层配置
         * 可见容器在启动过程中就会创建组件对象，把单实例的对象创建完毕
         * 组件在容器中是单实例的，每次获取直接从容器中拿就可以了
         *
         * 可以使用配置类来进行分类管理组件
         * 配置类也是一个组件
         *
         */

        /**
         * 1.@Controller 控制器组件
         * 2.@Service    服务层组件
         * 3.@Repository 持久层组件
         * 4.@Component  其他组件
         * 5.@ComponentScan(basePackages = "com.jiehfut") 主程序使用该注解设置扫描路径 => 批量扫描该路径下使用注解的组件
         *
         */
        UserController userController = ioc.getBean(UserController.class);
        System.out.println("userController = " + userController);
        // userController = com.jiehfut.assmexample.controller.UserController@2d84cb86

        /**
         * 导入第三方组件
         * 1.自己 new 出来一个对象后使用 @Bean 将其注册进容器中
         * 2.通过注解导入 @Import(Arrays.class) 整个项目标注一次即可，可以专门写一个配置类进行整合
         */
        Map map = ioc.getBean(HashMap.class);
        System.out.println("map = " + map); // map = {}


        /**
         * @Scope 调整组件的作用域
         * 1.@Scope("prototype")，表示该组件为 非单实例
         *     容器启动的时候不会创建非单实例对象，什么时候获取什么时候创建
         *     为
         * 2.@Scope("singleton")，表示该组件为 单实例，默认值
         *     容器启动会创建单实例对象，容器启动完成前会创建该组件对象
         *     可以使用 @lazy 注解表明该组件为懒加载
         * 3.@Scope("request")，表示该组件为 同一个请求的单实例
         * 4.@Scope("session")，表示该组件为 同一次会话的单实例
         *
         */
        Object firstCat = ioc.getBean("first-cat");
        System.out.println("firstCat = " + firstCat);
        // first-cat 组件使用 @Scope("prototype") 修饰，可见在 ioc 容器启动过程中没有进行创建，在获取该对象的时候才会创建该组件

        Object secondCat = ioc.getBean("second-cat");
        System.out.println("secondCat = " + secondCat);
        // second-cat 组件使用 @scope("singleton") && @lazy 注解使该组件在使用的时候才会创建，不会在容器启动的时候就去创建




        /**
         * 还可以使用 FacyoryBean 进行组件的创建
         * FacyoryBean 是一种特殊的组件
         *
         * FacyoryBean 在容器中放置的组件的类型是接口中指定的泛型
         *             组件名称是工厂自己的名字
         */
        Car car = ioc.getBean(Car.class);
        System.out.println("car = " + car);
        Map<String, Car> beansOfType = ioc.getBeansOfType(Car.class);
        System.out.println(beansOfType);
        // {cheryFactory=Car(brand=chery, model=small, color=red)}





        /**
         * 根据条件来创建组件（条件注册）
         * 判断当前电脑的操作系统是 Windows 还是 Mac
         * 如果是 Windows 系统，容器中放置 bill
         *       Mac 系统，容器中放置 jo
         * @Conditional(WindowsCondition.class) 如果标注在类头上，表示如果不满足条件，该类不起作用
         */
        // 打印所有的 Person 对象
        System.out.println("打印所有的 person 对象，检查根据条件来创建组件的结果：");
        Map<String, Person> beansOfType1 = ioc.getBeansOfType(Person.class);
        for (Map.Entry<String, Person> entry : beansOfType1.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
        // 拿到环境变量
        ConfigurableEnvironment environment = ioc.getEnvironment();
        // 拿到操作系统
        String property = environment.getProperty("OS");
        // 当前电脑的操作系统
        System.out.println("property = " + property);


        /**
         * 测试 spring 自带的进行判断的条件
         */
        System.out.println("测试 spring 自带的进行判断的条件：");
        Map<String, Duck> beansOfType2 = ioc.getBeansOfType(Duck.class);
        for (Map.Entry<String, Duck> entry : beansOfType2.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }


        /**
         * 组件的依赖注入
         * 先类型，后名字
         */
        System.out.println("组件的依赖注入：");
        UserController controller = ioc.getBean(UserController.class);
        System.out.println(controller);


        /**
         * 使用 @Value 注解对类的默认属性进行赋值
         */
        Chicken chicken = ioc.getBean(Chicken.class);
        System.out.println("chicken = " + chicken);
        // chicken = Chicken(name=胖大鸡, age=4, high=260,
        // id=6bff7b4f-d123-4af0-82a8-5b421c4e70d8, message=hello, flag=false, method=you never know)
        // 取不到使用默认值
        Horse horse = ioc.getBean(Horse.class);
        System.out.println("horse = " + horse);


        /**
         * 使用 @ResourceUtils 获取资源
         */
        File file = ResourceUtils.getFile("classpath:路飞幼态.jpg");
        System.out.println("file = " + file);
        int length = new FileInputStream(file).available();
        System.out.println("文件大小：length = " + length); // 文件大小：length = 37042

        /**
         * 多环境，多数据源
         * application.properties 配置运行环境
         */
        DataSources dataSources = ioc.getBean(DataSources.class);
        System.out.println("运行环境 = " + dataSources);


        /**
         * spring 组件的生命周期
         * 可见在创建 ioc 容器的过程中
         * 1.先调用组件的构造器来进行组件的创建
         *   进行属性注入 setter
         *   这是注解 @PostConstruct 标明的用于在构造器完成之后进行执行的方法...
         *   InitializingBean 接口中的方法，在所有的赋值完成之后执行：afterPropertiesSet
         * 2.调用组件中指定的初始化方法
         *   ioc 容器创建完成
         *   运行中...
         *   这是注解 @PreDestroy 标明的用于在组件销毁之前进行执行的方法...
         *   DisposableBean 接口中的方法，在所有的赋值完成之后执行：destroy
         * 3.最后使用完毕调用组件中指定的销毁方法
         *
         */
        System.out.println("====spring 组件的生命周期 ==== 运行 ====");
        SpringLifeCycle lifeCycle = ioc.getBean(SpringLifeCycle.class);
        System.out.println("lifeCycle = " + lifeCycle);

        // SpringLifeCycle 的无参构造器方法...
        // chery factory 正在制造汽车...
        // SpringLifeCycle 进行依赖注入Car(brand=chery, model=small, color=red)
        // 这是注解 @PostConstruct 标明的用于在构造器完成之后进行执行的方法...
        // InitializingBean 接口中的方法，在所有的赋值完成之后执行：afterPropertiesSet...
        // @bean 创建的 s-lifecycle 组件的初始化方法
        // 2024-12-25T17:28:27.559+08:00  INFO 8916 --- [a-ssm-example] [           main] c.j.assmexample.ASsmExampleApplication   : Started ASsmExampleApplication in 1.086 seconds (process running for 1.546)
        // ===== ioc 容器创建完成 =======
        // ====spring 组件的生命周期 ==== 运行 ====
        // lifeCycle = SpringLifeCycle(id=0, name=null, car=Car(brand=chery, model=small, color=red))
        // 这是注解 @PreDestroy 标明的用于在组件销毁之前进行执行的方法...
        // DisposableBean 接口中的方法，在所有的赋值完成之后执行：destroy...
        // @bean 创建的 s-lifecycle 组件的销毁方法




    }










    public static void testBeanAnnotation(String[] args) {
        // 跑起来一个 spring 的应用：即为 IoC 容器
        // 1.SpringBoot 创建并且管理 Spring 上下文：ApplicationContext
        // public interface ConfigurableApplicationContext extends ApplicationContext, Lifecycle, Closeable {}
        ConfigurableApplicationContext ioc = SpringApplication.run(ASsmIocApplication.class, args);
        System.out.println("ioc = " + ioc);
        // ioc = org.springframework.context.annotation.AnnotationConfigApplicationContext@528c868


        // 2.ioc 容器中有哪些组件（一个类就算一个组件）
        String[] beanDefinitionNames = ioc.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }

        // TODO:获取容器中的组件对象：组件的四大特性：名字，类型，对象，作用域
        Object person = ioc.getBean("person");
        System.out.println("按照名字拿到组件对象：" + person);
        // 按照名字拿到组件对象：PersonConfig(name=person, age=0, gender=男)
        // 如果组件不存在：NoSuchBeanDefinitionException

        // TODO:按照组件类型获取对象
        // PersonConfig person1 = (PersonConfig) ioc.getBean(PersonConfig.class);
        // System.out.println("按照组件类型获取组件对象：" + person1);
        // Dog dog = (Dog) ioc.getBean(Dog.class);
        // System.out.println("按照组件类型获取组件对象：" + dog);
        // 按照组件类型获取组件对象：PersonConfig(name=person, age=0, gender=男)
        // 按照组件类型获取组件对象：Dog(name=dog, age=0)
        // 没有该组件对象：NoSuchBeanDefinitionException

        // TODO:组件有多个
        // PersonConfig person2 = (PersonConfig) ioc.getBean(PersonConfig.class);
        // System.out.println("该组件类型有多个：" + person2);
        // 组件不存在异常：NoSuchBeanDefinitionException
        // 组件不唯一异常：NoUniqueBeanDefinitionException


        // todo: 组件有多个的时候，按照组件的类型获取所有对象
        Map<String, Person> beansOfType = ioc.getBeansOfType(Person.class);
        System.out.println("组件对象有多个，按照组件类型获取全部组件：");
        for (Map.Entry<String, Person> entry : beansOfType.entrySet()) {
            System.out.println(entry.getValue());
        }
        // 组件对象有多个，按照组件类型获取全部组件：
        // PersonConfig(name=person, age=0, gender=男)
        // PersonConfig(name=zhangsan, age=0, gender=男)
        // PersonConfig(name=lisi, age=0, gender=男)


        // todo:组件不唯一，但是只要一个组件对象：lisi.  如果组件名称（value中值）不唯一，获取先注册的对象，没有先后顺序就按字母顺序
        Object lisi = ioc.getBean("lisi");
        System.out.println("组件名称一定全局唯一：李四 = " + lisi);
        // 组件名称一定全局唯一：李四 = PersonConfig(name=lisi, age=0, gender=男)


        // todo:按照类型加名字
        Person lisi1 = ioc.getBean("lisi", Person.class);
        System.out.println("按照类型加组件名称：" + lisi1);
        // 按照类型加组件名称：PersonConfig(name=lisi, age=0, gender=男)

    }


    /**
     * 没有 springBoot 的时候，进行组件注册时候的操作
     * @param args
     */
//    public static void mainPre(String[] args) {
//        // 1.以前的 spring 需要自己创建一个 ioc 容器，从类路径下找资源
//        ClassPathXmlApplicationContext ioc =
//                new ClassPathXmlApplicationContext("classpath:ioc.xml");
//        // 也可以从文件路径下找配置
//        FileSystemXmlApplicationContext IOC =
//                new FileSystemXmlApplicationContext();
//        // 2.在 ioc.xml 配置文件中需要自己配置，原容器中是没有组件的，是一个空的；在配置中放什么组件有什么组件
//        String[] beanDefinitionNames = ioc.getBeanDefinitionNames();
//        for (String beanDefinitionName : beanDefinitionNames) {
//            System.out.println(beanDefinitionName);
//        }
//    }






}
