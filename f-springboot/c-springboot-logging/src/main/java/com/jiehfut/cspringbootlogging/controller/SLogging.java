package com.jiehfut.cspringbootlogging.controller;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class SLogging {

    // 1.一个类可以拥有一个日志类，拿到当前类的日志记录器，可以使用注解注入
    Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/self-log")
    public String selfLog(String param1, String param2) {
        // 2.
        // log.info("slf4j 自定义信息！");
        logger.trace("自定义 trace 日志信息！");
        logger.debug("自定义 debug 日志信息！");
        logger.info("自定义 info 日志信息！param1={},param2={}", param1, param2);
        logger.warn("自定义 warn 日志信息！");
        logger.error("自定义 error 日志信息！");
        // 2025-01-01T21:36:26.198+08:00  INFO 85644 --- [c-springboot-logging] [nio-8080-exec-1] c.j.c.controller.SLogging                : 自定义 info 日志信息！param1=str1,param2=str2
        // 2025-01-01T21:36:26.198+08:00  WARN 85644 --- [c-springboot-logging] [nio-8080-exec-1] c.j.c.controller.SLogging                : 自定义 warn 日志信息！
        // 2025-01-01T21:36:26.198+08:00 ERROR 85644 --- [c-springboot-logging] [nio-8080-exec-1] c.j.c.controller.SLogging                : 自定义 error 日志信息！

        /**
         * 可以通过文件保存日志信息
         * 1.进行日志文件配置 application.properties
         * 2.日志归档 && 切割
         */
        for (int i = 0; i < 1000; i++) {
            logger.trace("自定义 trace 日志信息！");
            logger.debug("自定义 debug 日志信息！");
            logger.info("自定义 info 日志信息！param1={},param2={}", param1, param2);
            logger.warn("自定义 warn 日志信息！");
            logger.error("自定义 error 日志信息！");
        }
        return "self-log";

    }


}
