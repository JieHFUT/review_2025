package com.jiehfut.springmybatisplus;

import com.jiehfut.springmybatisplus.pojo.User;
import com.jiehfut.springmybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MybatisPlusServiceTest {
    @Autowired
    private UserService userService;
    
    @Test // 查询表中的总的记录数
    public void testAllCount(){
        long count = userService.count();
        System.out.println("count = " + count);
        // SELECT COUNT( * ) AS total FROM user
    }

    @Test // 进行批量插入的操作
    public void testBatchInsert(){
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setName("zhangsan");
        user1.setAge(18);
        user1.setEmail("346122@163.com");
        User user2 = new User();
        user2.setName("lisi");
        user2.setAge(23);
        user2.setEmail("43652342@163.com");
        User user3 = new User();
        user3.setName("wangwu");
        user3.setAge(63);
        user3.setEmail("82146172@163.com");
        users.add(user1);
        users.add(user2);
        users.add(user3);
        
        boolean saved = userService.saveBatch(users);
        System.out.println("saved = " + saved);
        // 单个添加操作通过循环来实现的
        // INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
    }









}
