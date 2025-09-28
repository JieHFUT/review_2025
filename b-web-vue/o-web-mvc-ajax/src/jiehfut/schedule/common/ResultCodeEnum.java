package jiehfut.schedule.common;


/**
 * 这个枚举类是前后端约定的交互信息
 * 针对不同的响应状态
 * 后端按照约定向前端返回对应的状态码和信息
 */
public enum ResultCodeEnum {

    // 这五个枚举对象调用下面的私有的构造方法
    SUCCESS(200, "success"),
    USERNAME_ERROR(501, "usernameError"),
    PASSWORD_ERROR(502, "passwordError"),
    NOTLOGIN(503, "notlogin"),
    USERNAME_USED(504, "usernameUsed");







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
