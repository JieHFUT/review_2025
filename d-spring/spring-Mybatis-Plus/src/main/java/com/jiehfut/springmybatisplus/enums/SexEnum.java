package com.jiehfut.springmybatisplus.enums;


import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum SexEnum {

    MALE(1, "男"),
    FEMALE(1, "女");


    @EnumValue // 将注解所标识的属性注入数据库的字段中，而不是整个枚举类型
    Integer id;

    String sex;

    SexEnum(Integer id, String sex) {
        this.id = id;
        this.sex = sex;
    }
    

}
