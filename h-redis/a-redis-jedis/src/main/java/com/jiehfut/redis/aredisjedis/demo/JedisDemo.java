package com.jiehfut.redis.aredisjedis.demo;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

public class JedisDemo {
    // 与 jdbc 同理
    public static void main(String[] args) {
        // 1.测试连接 redis 服务器是否成功
        Jedis jedis = new Jedis("192.168.6.102", 6381);
        // 2.指定访问服务器的密码
        jedis.auth("959452");
        // 3.获得了 jedis 的客户端，可以像 jdbc 一样进行访问我们的 redis 服务
        System.out.println(jedis.ping());  // PONG



        // System.out.println(jedis.info());
        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println(key);
        }

        // 向主机中添加 key，获取 key
        jedis.set("k5", "redis-jedis");
        System.out.println(jedis.get("k5"));
        long ttl_k5 = jedis.ttl("k5");
        System.out.println(ttl_k5);
        jedis.expire("k5", 20L);

        jedis.del("k5");

        jedis.lpush("list", "11", "22", "33", "44");
        List<String> list = jedis.lrange("list", 0, -1);
        for (String s : list) {
            System.out.println(s);
        }

        /**
         考虑到 jedis 连接 redis 服务器，如果在一个高并发的情况下
         一秒钟八万次写入，需要 new 八万次连接，反复创建线程，消耗资源并且线程不安全 => 考虑使用池化技术

         lettuce 考虑到这个问题，改善后，后面被 springboot 招安

         */

    }
}
