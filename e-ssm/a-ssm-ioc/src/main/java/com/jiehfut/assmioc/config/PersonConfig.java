package com.jiehfut.assmioc.config;


import com.jiehfut.assmioc.bean.Person;
import com.jiehfut.assmioc.condition.MacCondition;
import com.jiehfut.assmioc.condition.WindowsCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration   // 告诉 spring 这是一个配置类
public class PersonConfig {

    /**
     3.在容器中注册自己的组件
     容器中的每一个组件都有自己的名字，方法名字就是组件的名字（注解的 value 值会覆盖方法名）
     */
    @Bean("person")
    @Primary // 声明为主组件，默认组件
    public Person personBean() {
        Person person = new Person();
        person.setName("person");
        person.setAge(0);
        person.setGender("男");
        return person;
    }
    @Bean("zhangsan")
    public Person zhangsanBean() {
        Person person = new Person();
        person.setName("zhangsan");
        person.setAge(0);
        person.setGender("男");
        return person;
    }
    @Bean("lisi")
    public Person lisiBean() {
        Person person = new Person();
        person.setName("lisi");
        person.setAge(0);
        person.setGender("男");
        return person;
    }



    /**
     * 根据条件来创建组件
     * 判断当前电脑的操作系统是 Windows 还是 Mac
     * 如果是 Windows 系统，容器中放置 bill
     *       Mac 系统，容器中放置 jobs
     */

    @Bean("bill")
    @Conditional(WindowsCondition.class) // 满足条件就把 bill 的组件注册到 ioc 容器中
    public Person bill() {
        Person person = new Person();
        person.setName("bill");
        person.setAge(67);
        person.setGender("female");
        return person;
    }


    @Bean("jobs")
    @Conditional(MacCondition.class) // 满足条件就把 jobs 的组件注册到 ioc 容器中
    public Person jobs() {
        Person person = new Person();
        person.setName("jobs");
        person.setAge(78);
        person.setGender("female");
        return person;
    }



}
