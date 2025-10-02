package com.jiehfut.dssmtransaction.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private Integer id;
    private String username;
    private Integer age;
    private BigDecimal money;

}
