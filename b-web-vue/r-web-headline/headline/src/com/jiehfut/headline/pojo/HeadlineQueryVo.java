package com.jiehfut.headline.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName: HeadlineQueryVo
 * Package: com.jiehfut.headline.pojo
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/12/22 20:08
 * @Version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HeadlineQueryVo implements Serializable {
    private String keyWords;
    private Integer type;
    private Integer pageNum;
    private Integer pageSize;
}
