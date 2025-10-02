package com.jiehfut.iocxml.array;

import java.util.Arrays;

public class Emp {

    // 员工属于哪个部门
    private Dept dept;

    // 员工的姓名和年龄
    private String ename;
    private Integer age;
    // 员工的爱好
    private String[] hobbies;

    public void work() {
        System.out.println(ename + " is " + age + " years old and go to work in " + dept.getDname() );
    }


    @Override
    public String toString() {
        return "Emp{" +
                "dept=" + dept +
                ", ename='" + ename + '\'' +
                ", age=" + age +
                ", hobbies=" + Arrays.toString(hobbies) +
                '}';
    }


    public String[] getHobbies() {
        return hobbies;
    }
    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }
    public Dept getDept() {
        return dept;
    }
    public void setDept(Dept dept) {
        this.dept = dept;
    }
    public String getEname() {
        return ename;
    }
    public void setEname(String ename) {
        this.ename = ename;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
}
