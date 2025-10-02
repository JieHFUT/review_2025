package com.jiehfut.bspringbootinitializr.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {


    // 配置 redis 后，redis 的自动配置类给容器中注入了一些组件，用于 redis 的增删改查
    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping("/incr")
    public String incr() {
        Long redisIncr = redisTemplate.opsForValue().increment("redis incr");
        return "增加后的值：" + redisIncr;
    }




}
