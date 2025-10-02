package com.jiehfut.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp implements Serializable {
    private static final long serialVersionUID = 14233214213424L;

    private Integer eid;
    private String empName;
    private Integer age;
    private String sex;
    private String email;


    /**
     * 处理多对一的关系
     * 
     */
    private Dept dept;


}
