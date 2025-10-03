package com.jiehfut.iocxml.map;

public class Lesson {
    /**
     用于展示引用类型的注入问题：包括数组和 map 集合

     */
    private String lname;

    @Override
    public String toString() {
        return "Lesson{" +
                "lname='" + lname + '\'' +
                '}';
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
}
