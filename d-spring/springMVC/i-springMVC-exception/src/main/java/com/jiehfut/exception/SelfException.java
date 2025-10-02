package com.jiehfut.exception;

/**
 * ClassName: SelfException
 * Package: com.jiehfut.exception
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/12/8 18:51
 * @Version 1.0
 */
public class SelfException extends RuntimeException {

    private static final long serialVersionUID = 132895741329856L;

    public SelfException() {}

    public SelfException(String message) {
        super(message);
    }

    public SelfException(String message, Throwable cause) {
        super(message, cause);
    }

}
