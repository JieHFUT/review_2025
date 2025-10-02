package com.jiehfut.assmioc.processor;


import com.jiehfut.assmioc.bean.SpringLifeCycle;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component  // 拦截所有 bean 的后置处理器
public class LifeCycleBeanPostProcessor implements BeanPostProcessor {
    /**
     * public interface BeanPostProcessor { // 堪称组件的外挂修改器
     *     @Nullable      后置处理，在初始化之前
     *     default Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
     *         return bean;
     *     }
     *     @Nullable      后置处理，在初始化之后
     *     default Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
     *         return bean;
     *     }
     * }
     */

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("后置处理器 postProcessBeforeInitialization 在初始化之前执行..." + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("后置处理器 postProcessAfterInitialization 在初始化之后执行..." + beanName);
        if (bean instanceof SpringLifeCycle newSpringLifeCycleName) {
            newSpringLifeCycleName.setName("newSpringLifeCycleName");
        }
        return bean;
    }


    /**
     * ********* SpringLifeCycle 的无参构造器方法...
     * chery factory 正在制造汽车...
     * ********* SpringLifeCycle 进行依赖注入（setCar） Car(brand=chery, model=small, color=red)
     *
     *
     * 后置处理器 postProcessBeforeInitialization 在初始化之前执行...s-lifecycle
     * ********* 这是注解 @PostConstruct 标明的用于在构造器完成之后进行执行的方法...
     * ********* InitializingBean 接口中的方法，在所有的赋值完成之后执行：afterPropertiesSet...
     * ********* @bean 创建的 s-lifecycle 组件的初始化方法
     * 后置处理器 postProcessAfterInitialization 在初始化之后执行...s-lifecycle
     *
     *
     * =====ioc 容器创建完成========
     * ====spring 组件的生命周期 ==== 运行 ====
     * lifeCycle = SpringLifeCycle(id=0, name=null, car=Car(brand=chery, model=small, color=red))
     * ********* 这是注解 @PreDestroy 标明的用于在组件销毁之前进行执行的方法...
     * ********* DisposableBean 接口中的方法，在所有的赋值完成之后执行：destroy...
     * ********* @bean 创建的 s-lifecycle 组件的销毁方法
     */



    /**
     * 可以考虑，@Autowired 是如何实现属性注入的
     * public interface BeanPostProcessor {}
     *     public interface MergedBeanDefinitionPostProcessor {}
     *         public class AutowiredAnnotationBeanPostProcessor {}
     * 专门有一个处理 @Autowired 注解的实现类  AutowiredAnnotationBeanPostProcessor
     * 每一个 bean 创建以后都会被 BeanPostProcessor 里面的 postProcessBeforeInitialization 拦截
     * 该方法就会利用反射来获取当前 bean 组件的所有属性，利用反射，得到 bean 组件属性上标注的所有注解
     * 如果注解是 @Autowired，就去 ioc 容器中去找到这个属性对应的组件（先按类型，再按名称）找到
     * 
     */


}
