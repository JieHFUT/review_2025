package com.jiehfut.essmwebmvc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String username;
    private String password;
    private String cellphone;
    private boolean agreement;

    // 级联
    private Address address;

    private String sex;
    private String[] hobby;
    private String grade;
}
