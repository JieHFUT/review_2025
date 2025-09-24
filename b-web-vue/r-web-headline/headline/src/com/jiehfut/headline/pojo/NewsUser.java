package com.jiehfut.headline.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName: NewsUser
 * Package: com.jiehfut.headline.pojo
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/12/22 20:05
 * @Version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewsUser implements Serializable {
    private Integer uid;
    private String username;
    private String userPwd;
    private String nickName;
}
