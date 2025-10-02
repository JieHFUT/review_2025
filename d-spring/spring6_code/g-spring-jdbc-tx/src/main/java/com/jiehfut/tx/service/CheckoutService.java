package com.jiehfut.tx.service;

/**
 * ClassName: CheckoutService
 * Package: com.jiehfut.tx.service
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/12/2 22:07
 * @Version 1.0
 */
public interface CheckoutService {
    // 一次性买多本书的方法
    void checkout(Integer[] bookId, Integer userId);
}
