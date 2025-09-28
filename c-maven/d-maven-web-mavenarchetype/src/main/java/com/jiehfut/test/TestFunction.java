package com.jiehfut.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ClassName: TestFunction
 * Package: com.jiehfut.test
 * Description:
 * alt + 7：打开类结构
 * @Author jieHFUT
 * @Create 2024/11/26 21:38
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestFunction {

    private Integer id;

    private String user;

    private Date date;

}
