package com.jiehfut.selfioc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 对注解要用原注解声明一些信息
 1.该注解在哪里可以使用： @Target 如本注解中代表类，接口上可以使用
 2.该注解作用范围，在什么时候会生效： @Retention，如本注解中代表运行期生效
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Bean {

}
