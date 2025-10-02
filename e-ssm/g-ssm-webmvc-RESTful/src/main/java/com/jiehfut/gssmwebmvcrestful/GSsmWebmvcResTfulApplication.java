package com.jiehfut.gssmwebmvcrestful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GSsmWebmvcResTfulApplication {
    /**
     * 调用别人的功能有几种方式？
     * 1.API：给第三方发送请求，获取响应数据
     * 2.SDK：导入 jar 包
     *
     * create:创建
     * retrive:查询
     * update:修改
     * delete:删除
     *
     * @param args
     */

    public static void main(String[] args) {
        SpringApplication.run(GSsmWebmvcResTfulApplication.class, args);
    }

}
