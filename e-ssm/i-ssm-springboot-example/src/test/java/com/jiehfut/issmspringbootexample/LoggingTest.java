package com.jiehfut.issmspringbootexample;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;



@Slf4j
@SpringBootTest
public class LoggingTest {

    // 1.获取一个日志记录器（指定哪个类的）
    Logger logger = LoggerFactory.getLogger(LoggingTest.class);

    @Test
    public void testLogging(){

        /** 2.记录日志
        System.out.println("=========== 日志 =========");
        logger.trace("这是一个日志：Trace");
        logger.debug("这是一个日志：Debug");
        logger.info("这是一个日志：Info");
        logger.warn("这是一个日志：Warn");
        logger.error("这是一个日志：Error"); */

        log.trace("这是一个日志：Trace");
        log.debug("这是一个日志：Debug");
        log.info("这是一个日志：Info");
        log.warn("这是一个日志：Warn");
        log.error("这是一个日志：Error");

        /**
         ****** 日志级别：all trace（跟踪）- debug（调试）- info（默认）
         ******                        - warn（警告）- error（错误）- fatal（致命错误）- off（关掉日志）
         ****** 默认日志基本是 info，只会打印默认级别及其之上的所有信息
         ****** 默认日志级别需要在全局配置里面调整根级别：logging.level.root=
         ****** 如果哪个类或者包不说日志级别，就会使用默认的根级别
         ****** 可以设置某个包或者类的：logging.level.com.jiehfut.issmspringbootexample=wran
         *
         ****** 或者进行级别分组：logging.group.biz=com.first,com.second,com.third
         ******               logging.level.biz=error
         ****** 如设置 mapper 这个包的默认日志是：debug，输出 sql 语句
         ****** 日志存到文件中（指定文件路径 && 文件名）：logging.file.name=
         ******                                   logging.file.path=
         ****** 日志输出形式：logging.pattern.console/file=
         ****** 如果不想在 springboot 中配置日志，可以使用自己的配置，在当前项目下放置一个自己的配置文件，就以自己的为准（如：logback.xml）
         ******
         ****** 如果想要更换日志：自己在 pom.xml 中去导入基础 spring-boot-starter，然后排除默认的 logging，添加其他的日志
         ****** 如关闭 logback，使用 log4j2
         ****** 可以自己再去配置一个 log4j2.xml 配置新的日志
         *         <dependency>
         *             <groupId>org.springframework.boot</groupId>
         *             <artifactId>spring-boot-starter</artifactId>
         *             <exclusions>
         *                 <exclusion>
         *                     <groupId>org.springframework.boot</groupId>
         *                     <artifactId>spring-boot-starter-logging</artifactId>
         *                 </exclusion>
         *             </exclusions>
         *         </dependency>
         *         <dependency>
         *             <groupId>org.springframework.boot</groupId>
         *             <artifactId>spring-boot-starter-log4j2</artifactId>
         *         </dependency>
         * 总的来说，使用日志就两步
         * 1.配置（日志输出位置，日志输出级别）
         * 2.记录日志（在代码的合适的地方进行输出）
         *
         *
         2025-01-15T12:07:41.127+08:00  WARN 14652 --- [i-ssm-springboot-example] [           main] ion$DefaultTemplateResolverConfiguration : Cannot find template location: classpath:/templates/ (please add some templates, check your Thymeleaf configuration, or set spring.thymeleaf.check-template-location=false)
         2025-01-15T12:07:41.428+08:00  INFO 14652 --- [i-ssm-springboot-example] [           main] c.j.issmspringbootexample.LoggingTest    : Started LoggingTest in 2.593 seconds (process running for 3.933)
         Java HotSpot(TM) 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended
         =========== 日志 =========
         2025-01-15T12:07:42.439+08:00  INFO 14652 --- [i-ssm-springboot-example] [           main] c.j.issmspringbootexample.LoggingTest    : 这是一个日志：Info
         2025-01-15T12:07:42.439+08:00  WARN 14652 --- [i-ssm-springboot-example] [           main] c.j.issmspringbootexample.LoggingTest    : 这是一个日志：Warn
         2025-01-15T12:07:42.439+08:00 ERROR 14652 --- [i-ssm-springboot-example] [           main] c.j.issmspringbootexample.LoggingTest    : 这是一个日志：Error
         2025-01-15T12:07:42.469+08:00  INFO 14652 --- [i-ssm-springboot-example] [ionShutdownHook] com.alibaba.druid.pool.DruidDataSource   : {dataSource-0} closing ...

         */

        // 追踪日志
        log.trace("");
        try{
            if ("1".equals(log)) {
                // 记录调试
                log.debug("");
            }
            // 记录信息
            log.info("");
            // 对容易出错误的设置警告
            log.warn("");
        } catch (Exception exception) {
            // 打印错误信息
            log.error("");
        }

        /**
         * 归档与切割
         * 配置日志的归档和切割：
         *     归档后日志文件的命名方式：logging.logback.rollingpolicy.file-name-pattern=${LOG_FILE}.%d{yyyy-MM-dd}.%i.gz
         *     应用启动时是否清楚以前日志：logging.logback.rollingpolicy.clean-history-on-start=false
         *     所有日志最多可以容纳多大（默认GB）：logging.logback.rollingpolicy.total-size-cap=1
         *     每个日志的最大值（默认 1mb）：logging.logback.rollingpolicy.max-file.size=10
         *     日志的最大保存天数：logging.logback.rollingpolicy.max-history=7
         *
         *     如果出发删除操作，优先删除较老的日志记录
         */
        String string = "first position";
        log.info("这是日志使用过程中的占位符号：{}，{}，{}",string, "second position", "third" + " position");
    }
}
