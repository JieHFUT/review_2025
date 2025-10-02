package com.jiehfut.dspringbootweb3.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private int id;
    private String username;
    private String password;
    private int age;
    private String email;
    private String gender;
    private String type;


}
