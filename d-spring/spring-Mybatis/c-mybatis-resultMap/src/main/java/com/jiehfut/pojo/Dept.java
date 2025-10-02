package com.jiehfut.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dept {
    private Integer did;
    private String deptName;

    // 设置一对多的集合
    private List<Emp> empList;
}
