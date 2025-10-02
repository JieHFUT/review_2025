package com.jiehfut.assmioc.dao;


import com.jiehfut.assmioc.bean.Duck;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@ToString
@Repository
public class UserDao {

    Duck duck;

    /**
     * 推荐方法：通过构造器注入
     * 有参构造器，spring 自动去容器中去寻找构造器需要的所有参数的组件值
     */
    public UserDao(@Qualifier("ahua") Duck duck) {
        System.out.println("UserDao 带参构造器..." + duck);
        this.duck = duck;
    }

    // 或者
//    @Autowired
//    public void setDuck(@Qualifier("ada") Duck duck){
//        System.out.println("UserDao 带参 setter 方法..." + duck);
//        this.duck = duck;
//    }







}
