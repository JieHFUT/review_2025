package com.jiehfut.hssmwebmvcpractice.annotation;


import com.jiehfut.hssmwebmvcpractice.validator.AddressValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * 自定义注解
 * 要求此注解在某一个参数上，该参数的地址只能是以下信息
 * 1.安徽省芜湖市
 * 2.上海市浦东新区
 * 3.江苏省南京市
 *
 */
@Documented
@Constraint( // 约束
        validatedBy = {AddressValidator.class} // 被校验器所正常，需要写一个校验器支持该注解的校验规则（填入该校验器的全类名）
)
@Target({ElementType.FIELD}) // 标注在属性上
@Retention(RetentionPolicy.RUNTIME)
public @interface Address {

    String message() default "{jakarta.validation.constraints.NotNull.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {}; // 负载
}
