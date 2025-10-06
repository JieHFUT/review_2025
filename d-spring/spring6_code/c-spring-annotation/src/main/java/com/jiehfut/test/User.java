package com.jiehfut.test;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Component(value = "user")  // 相当于在 xml 文件中：<bean id = "uesr", class = "path"></bean>
                            // value 的默认值是类名小写
public class User {
    // 四个注解的效果是一样的，不同是为了方便见名知意
    //    @Component
    //    @Repository
    //    @Controller
    //    @Service
}
