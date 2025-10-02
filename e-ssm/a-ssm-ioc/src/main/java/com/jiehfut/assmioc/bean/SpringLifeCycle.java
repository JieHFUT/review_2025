package com.jiehfut.assmioc.bean;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class SpringLifeCycle implements InitializingBean, DisposableBean {

    private int id;
    private String name;

    /**
     * 如果该组件中需要注入一些组件
     * 是什么时候进行依赖注入的呢？
     */
    public Car car;

    @Autowired
    public void setCar(Car car) {
        System.out.println("********* SpringLifeCycle（setCar） 进行依赖注入" + car);
        this.car = car;
    }


    public SpringLifeCycle() {
        System.out.println("********* SpringLifeCycle 的无参构造器方法...");
    }


    // 该组件 s-lifecycle 的初始化方法
    public void initLifeCycle() {
        System.out.println("********* @bean 创建的 s-lifecycle 组件的初始化方法");

    }

    // 该组件 s-lifecycle 的销毁方法
    public void destroyLifeCycle() {
        System.out.println("********* @bean 创建的 s-lifecycle 组件的销毁方法");

    }









    /**
     * InitializingBean 接口中的方法
     * 在属性设置之后进行调用，即在所有的 setter 方法赋值完成之后
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("********* InitializingBean 接口中的方法，在所有的赋值完成之后执行：afterPropertiesSet... ");
    }

    /**
     * DisposableBean 接口中的方法
     * 在组件被销毁之前被调用
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("********* DisposableBean 接口中的方法，在所有的赋值完成之后执行：destroy... ");
    }






    /**
     * 也可以随便指定两个方法，不需要配置，不需要实现接口中的方法，使用注解即可完成生命周期的拦截
     * @PostConstruct：标明的用于在构造器完成之后进行执行的方法...
     * @PreDestroy：标明的用于在组件销毁之前进行执行的方法...
     */
    @PostConstruct
    public void postConstruct() {
        System.out.println("********* 这是注解 @PostConstruct 标明的用于在构造器完成之后进行执行的方法...");
    }
    @PreDestroy
    public void preDestroy() {
        System.out.println("********* 这是注解 @PreDestroy 标明的用于在组件销毁之前进行执行的方法...");
    }


    /**
     * BeanPostProcessor
     *
     */





}
