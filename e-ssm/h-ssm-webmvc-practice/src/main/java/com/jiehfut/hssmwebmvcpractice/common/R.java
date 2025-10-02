package com.jiehfut.hssmwebmvcpractice.common;

import com.jiehfut.hssmwebmvcpractice.exception.BizExceptionEnume;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "统一返回对象")
@AllArgsConstructor
@NoArgsConstructor
public class R<T> {

    @Schema(description = "状态码")
    private int code;

    @Schema(description = "描述信息")
    private String msg;

    @Schema(description = "响应对象")
    private T data;

    public static R ok() {
        return new R(200, "success", null);
    }

    public static R ok(Object data) {
        return new R(200, "success", data);
    }

    public static R error(String msg, Object data) {
        return new R(-1, msg, data);
    }

    public static R error(String msg) {
        return new R(-1, msg, null);
    }

    public static R error(int code, String msg) {
        return new R(code, msg, null);
    }

    public static R error(BizExceptionEnume bizExceptionEnume, Object data) {
        return new R(bizExceptionEnume.getCode(), bizExceptionEnume.getMsg(), data);
    }
}
