package com.jiehfut.lifecycle;

public class User {

    private String name;

    public User() {
        System.out.println("1.调用无参构造创建 bean 对象...");
    }

    public void setName(String name) {
        System.out.println("2.调用 setter 方法给 bean 对象设置属性值...");
        this.name = name;
    }


    // 初始化方法（在 bean-lifecycle.xml 中指定）
    public void initMethod() {
        System.out.println("4.bean 对象初始化，调用指定的初始化方法...");
    }

    // 销毁方法（在 bean-lifecycle.xml 中指定）
    public void destroyMethod() {
        System.out.println("7.bean 对象销毁，调用指定的销毁方法...");
    }

    public String getName() {
        return name;
    }


}
