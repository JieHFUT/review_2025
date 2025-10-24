package com.jiehfut.redis.aredisjedis.demo;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

import java.util.List;

public class LettuceDemo {

    public static void main(String[] args) {
        // 1.使用构建器链式编程来 build 我们的 redisURI
        RedisURI uri = RedisURI.builder()
                .redis("192.168.6.102")
                .withPort(6381)
                .withAuthentication("default", "959452")
                .build();

        // 2.创建连接客户端
        RedisClient redisClient = RedisClient.create(uri);
        StatefulRedisConnection<String, String> connect = redisClient.connect();

        // 3.创建操作的 command
        RedisCommands<String, String> commands = connect.sync();

        //===================== 业务操作 ==================
        List<String> keys = commands.keys("*");
        for (String key : keys) {
            System.out.println(key);
        }

        commands.set("letture", "first-letture");
        commands.expire("letture", 10L);
        //===============================================

        // 4.开始进行关闭释放资源
        connect.close();
        redisClient.shutdown();
    }
}
