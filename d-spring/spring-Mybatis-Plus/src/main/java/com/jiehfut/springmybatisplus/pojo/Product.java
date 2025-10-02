package com.jiehfut.springmybatisplus.pojo;


import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long id;
    private String name;
    private Integer price;

    @Version // 声明这是一个乐观锁的注解
    private Integer version;
}

