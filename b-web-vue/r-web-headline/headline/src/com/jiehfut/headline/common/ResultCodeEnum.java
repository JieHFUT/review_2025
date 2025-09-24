package com.jiehfut.headline.common;

/**
 * ClassName: ResultCodeEnum
 * Package: com.jiehfut.headline.common
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/12/22 19:56
 * @Version 1.0
 */


/**
 * 统一返回结果状态信息类
 *
 */
public enum ResultCodeEnum {

    SUCCESS(200,"success"),
    USERNAME_ERROR(501,"usernameError"),
    PASSWORD_ERROR(503,"passwordError"),
    NOTLOGIN(504,"notLogin"),
    USERNAME_USED(505,"userNameUsed")
    ;

    private Integer code;
    private String message;
    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public Integer getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}
