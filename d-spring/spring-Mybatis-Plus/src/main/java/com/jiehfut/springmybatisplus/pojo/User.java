package com.jiehfut.springmybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

@Data //lombok注解
// @TableName("t_user") // 设置实体类对应的表名，也可以在 application.yml 进行全局配置
public class User {

//    @TableId // 如果表中主键 && 类中属性 是uid, 主键名不是id，使用 @TableId 指定该字段为主键
//    private Long uid;

    // 如果表中主键是uid, 类中属性是id, 主键名称不是id，使用 @TableId(value = "uid") 指定该字段为主键
    @TableId(value = "uid", type = IdType.AUTO) // 使用 type 属性指定主键生成策略为自增，不使用默认的雪花算法，也可以全局配置
    private Long id;
    // 如果数据库字段是 user_name, 类中的属性是 userName，mybatis-plus 自动实现了两者类型的转换
    // 如果数据库字段是 user_name, 类中的属性是 name，需要通过注解来设置
    @TableField("user_name")
    private String name;
    private Integer age;
    private String email;

    @TableLogic // 代表这个字段变成了逻辑删除
    private Integer isDeleted;

}

