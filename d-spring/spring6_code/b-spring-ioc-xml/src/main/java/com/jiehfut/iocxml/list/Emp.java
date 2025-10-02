package com.jiehfut.iocxml.list;

public class Emp {

    // 员工属于哪个部门
    private Dept dept;

    // 员工的姓名和年龄
    private String ename;
    private Integer age;


    public void work() {
        System.out.println(ename + " is " + age + " years old and go to work in " + dept.getDname() );
    }

    @Override
    public String toString() {
        return "Emp{" +
                "dept=" + dept +
                ", ename='" + ename + '\'' +
                ", age=" + age +
                '}';
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
