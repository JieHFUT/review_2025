package com.jiehfut.cssmdatasource.config;

import com.jiehfut.cssmdatasource.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonConfig {

    @Bean("zhangsan")
    public Person zhangsan() {
        Person person = new Person();
        person.setId(1);
        person.setName("zhangsan");
        person.setAge(18);
        return person;
    }


}
