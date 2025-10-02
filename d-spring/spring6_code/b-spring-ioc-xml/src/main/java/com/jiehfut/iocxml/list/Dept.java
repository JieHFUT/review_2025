package com.jiehfut.iocxml.list;

import java.util.List;

// 部门和员工关系
public class Dept {

    private String dname;

    // 一个部门有很多员工
    private List<Emp> empList;

    public void info()  {
        System.out.println("部门名称：" + dname + "...");
        for (Emp emp : empList) {
            System.out.println(emp);
        }
    }




    @Override
    public String toString() {
        return "Dept{" +
                "dname='" + dname + '\'' +
                ", empList=" + empList +
                '}';
    }

    public List<Emp> getEmpList() {
        return empList;
    }
    public void setEmpList(List<Emp> empList) {
        this.empList = empList;
    }
    public String getDname() {
        return dname;
    }
    public void setDname(String dname) {
        this.dname = dname;
    }
}
