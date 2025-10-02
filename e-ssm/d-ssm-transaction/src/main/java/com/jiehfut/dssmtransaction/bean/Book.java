package com.jiehfut.dssmtransaction.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private Integer id;
    private String bookName;
    private BigDecimal price;
    private Integer stock;

}
