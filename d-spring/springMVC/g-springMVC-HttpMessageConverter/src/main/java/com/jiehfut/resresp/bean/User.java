package com.jiehfut.resresp.bean;


import org.springframework.stereotype.Repository;

@Repository
public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer age;
    private Integer sex;




    public User() {
    }

    public User(Integer id, Integer sex, Integer age, String password, String username) {
        this.id = id;
        this.sex = sex;
        this.age = age;
        this.password = password;
        this.username = username;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
