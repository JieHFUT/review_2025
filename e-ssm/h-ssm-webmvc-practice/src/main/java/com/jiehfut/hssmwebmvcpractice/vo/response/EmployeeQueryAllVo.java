package com.jiehfut.hssmwebmvcpractice.vo.response;


import com.jiehfut.hssmwebmvcpractice.annotation.Address;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeQueryAllVo {


    private Long id;

    private String name;

    // private Integer age; 不给前端响应年龄信息

    private String email;

    private String gender;

    private String address;

    private BigDecimal salary;



}
