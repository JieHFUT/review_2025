package com.jiehfut.jssmspringbootdeep;

import com.jiehfut.jssmspringbootdeep.service.AssertService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;


@Slf4j
@SpringBootTest
class JSsmSpringbootDeepApplicationTests {

    @Value("${outer.config}")
    private String outer;

    @Test
    void contextLoads() {
        /**
         * 外部化配置（只要在该项目打包 jar包的外面呢放一个配置文件，就不用重新打包，可以直接读取外部配置）
         * 假如这个属性 outer.config 需要被经常修改 - 外部配置
         * ***.jar => java -jar ***.jar 启动 => 该 jar 文件夹内放一个 applcation.properties
         * 在 applcation.properties 里面更改配置信息即可生效
         *
         * 外部优先
         * 激活优先
         * 命令行 > 最内层激活 > 次内层激活 > 最内层非激活 > ....
         */
        System.out.println("outer-config = " + outer);
    }




    @Autowired
    private AssertService assertService;

    @Test
    @DisplayName("测试断言...")
    public void testAssert(){
        String asserted = assertService.Assert();
        // 业务规定只有返回字符串才是成功
        log.error(asserted);

        // 使用断言（期望值，实际值，断言失败显示什么信息）
        Assertions.assertEquals("assert....", asserted, "断言结果：assertService.Assert() 并没有返回 assert....");
       /* Assertions.assertThrows(ArithmeticException.class, () -> {
            // 断定下面会出现数学异常
            assertService.Assert();
        });*/
        /**
         * 1.
         * 2.
         * 3.
         * 4.
         * 5.
         * 6.
         * 7.
         *
         */
        log.error("测试断言通过...");
    }

}
