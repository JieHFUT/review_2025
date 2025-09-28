package jiehfut.schedule.common;

import lombok.Data;

/**
 * 构建后端封装好的向前端返回的对象
 * @param <T>
 */
@Data
public class Result<T> {

    /**
     * 2.响应信息格式不规范
        "code":"",           ===> 业务状态码，本次请求的业务是否成功，如果失败了，是什么原因造成的？（不是响应报文中的响应码）
        "message":"",        ===> 业务状态码的补充描述，是对业务状态码的说明。
        "data":{"":"","":""......}            ===> 用来装本次响应的数据

       3.设置的错误的业务状态码
         SUCCESS(200, "success"),
         USERNAME_ERROR(501, "usernameError"),
         PASSWORD_ERROR(502, "passwordError"),
         NOTLOGIN(503, "notlogin"),
         USERNAME_USED(504, "usernameUsed");

       4.各种构造响应体方法（实际外面只使用两种）
         <T> Result<T> build(T body, ResultCodeEnum resultCodeEnum) 返回各种错误状态
         <T> Result<T> ok(T data) 返回成功的状态码 => return build(data, ResultCodeEnum.SUCCESS);

     */

    private Integer code;
    private String message;
    private T data;

    // 空参构造
    public Result() {}


    /**
     * 传入 data 构造响应 result 对象
     * @param data
     * @return
     * @param <T>
     */
    protected static <T> Result<T> build(T data) {
        Result<T> result = new Result<>();
        if (null != data) {
            result.setData(data);
        }
        return result;
    }


    /**
     * 传入 data code message 构造响应 result 对象
     * @param body
     * @param code
     * @param message
     * @return
     * @param <T>
     */
    public static <T> Result<T> build(T body, Integer code, String message) {
        Result<T> result = build(body);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }


    /**
     * 传入 data && 枚举对象构造 result 对象
     * @param body
     * @param resultCodeEnum
     * @return
     * @param <T>
     */
    public static <T> Result<T> build(T body, ResultCodeEnum resultCodeEnum) {
        Result<T> result = build(body);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }


    /**
     * 该方法用于成功的响应了前端的请求，只需要转入 data 即可
     * @param data
     * @return
     * @param <T>
     */
    public static <T> Result<T> ok(T data) {
        return build(data, ResultCodeEnum.SUCCESS);
    }








    public Result<T> message(String msg){
        this.setMessage(msg);
        return this;
    }

    public Result<T> code(Integer code){
        this.setCode(code);
        return this;
    }




}
