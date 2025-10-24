package com.jiehfut.redis.crediscluster.service;


import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Service
public class OrderService {

    public static final String ORER_KEY = "ord";

    @Resource
    private RedisTemplate redisTemplate;
    // private StringRedisTemplate stringRedisTemplate;

    // 添加订单信息
    public void addorder() {
        // 设置 k-v 值
        int keyId = ThreadLocalRandom.current().nextInt(1000) + 1; // 0-1000
        String serialNo = UUID.randomUUID().toString(); // 7e7f62b8-2bfa-44b2-a0e0-b27545f459ee

        String key = ORER_KEY + keyId;
        String value = "京东订单" + serialNo;
        // 调用 API 来设置键值
        redisTemplate.opsForValue().set(key, value);

        log.info("========= key:{}, value:{} ==========", key, value);
    }


    // 根据 ID 来获取订单信息
    public String getOrderById(Integer keyId) {
        return (String) redisTemplate.opsForValue().get(ORER_KEY + keyId);
    }

}
