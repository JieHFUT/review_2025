package com.jiehfut.headline.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName: HeadlinePageVo
 * Package: com.jiehfut.headline.pojo
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/12/22 20:09
 * @Version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HeadlinePageVo implements Serializable {
    private Integer hid;
    private String title;
    private Integer type;
    private Integer pageViews;
    private Long pastHours;
    private Integer publisher;
}
