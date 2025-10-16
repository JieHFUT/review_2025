package com.jiehfut.assmioc;

import com.jiehfut.assmioc.bean.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

// 测试主程序下
@SpringBootTest
class ASsmIocApplicationTests {

    @Autowired
    Person lisi;

    @Test
    void contextLoads() {
        System.out.println("name = " + lisi.getName());
    }

    @Test
    public void testUUID(){
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
    }



}
