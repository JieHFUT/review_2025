package com.jiehfut.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * ClassName: MavenTest
 * Package: com.jiehfut.test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/11/26 21:52
 * @Version 1.0
 */
public class MavenTest {
    @Test
    public void testAssert(){
        int a = 10;
        int b = 20;
        Assertions.assertEquals(a+b, 30);
        /**
         * mvn compile 只能来编译核心程序 ===> target
         *
         * 想要编译测试程序，需要使用
         * mvn test-compile  ===> test-classes
         * 对于测试代码来说，类和方法的命名都有一定的规范
         * 类需要以test开头或者test结尾
         * mvn test 指令会自动执行 maven 中的测试方法
         */
    }
}
