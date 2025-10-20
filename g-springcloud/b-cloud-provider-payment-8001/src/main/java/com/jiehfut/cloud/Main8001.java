package com.jiehfut.cloud;

import com.jiehfut.cloud.entities.PayDTO;
import com.jiehfut.cloud.resp.ResultData;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDiscoveryClient // 第三步（第一步在 pom.xml，第二步在 application.yml） 使用注解进行服务注册和发现
@RefreshScope // 主启动类添加该注解实现分布式配置的动态刷新（也可使用在 bootstrap.yaml - spring.cloud.consul.config.watch.wait-time=1 设置自动更改时间）
              // 也就是在 consul 中修改了配置文件，在微服务中立刻也跟着修改
@MapperScan("com.jiehfut.cloud.mapper") // import tk.mybatis.spring.annotation.MapperScan;
public class Main8001 {

    public static void main(String[] args) {
        SpringApplication.run(Main8001.class, args);
    }

    /**
     * 逆天异常
     * 因为在服务发现中心 consul 中进行分布式配置的时候
     * 在 config/cloud-payment-service/data 里面的 json 数据分割以 \t 分割导致 8001 服务无法启动
     *
     * ConsulPropertySources$PropertySourceNotFoundException
     * 修改为空格启动就可以了
     */

}
