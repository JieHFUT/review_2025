package com.jiehfut.redis.crediscluster.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @auther
 * @create
 * 如果不去配置，redis 在进行序列化的时候会发生错误，异常码值，有两种办法
 * 原因是因为一个使用 jdk 默认的序列化方法（十六进制），一个使用的是 RedisSerializer
 *
 * 1.使用 StringRedisTemplate，存入 key 不会出错，存入 value 还是有错误，可以使用
 *   redis-cli -a 959452 -p 6379 --raw 来启动 redis 对中文的支持
 * 2.配置下面这些内容（记得在集群中使用 -c --raw 启动客户端让其支持中文）
 */
@Configuration
public class RedisConfig {
    /**
     * redis 序列化的工具配置类，下面这个请一定开启配置
     * 127.0.0.1:6379> keys *
     * 1) "ord:102"  序列化过
     * 2) "\xac\xed\x00\x05t\x00\aord:102"   野生，没有序列化过
     * this.redisTemplate.opsForValue();  //提供了操作string类型的所有方法
     * this.redisTemplate.opsForList();   // 提供了操作list类型的所有方法
     * this.redisTemplate.opsForSet();   //提供了操作set的所有方法
     * this.redisTemplate.opsForHash();  //提供了操作hash表的所有方法
     * this.redisTemplate.opsForZSet();  //提供了操作zset的所有方法
     * @param lettuceConnectionFactory  这个工厂是将配置的信息注册进去
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {

        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();

        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        // 设置key序列化方式string - 使用这个序列化方式替换 jdk 默认16进制序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 设置value的序列化方式json，使用GenericJackson2JsonRedisSerializer替换默认序列化
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }
}
