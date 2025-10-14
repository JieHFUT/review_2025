package com.jiehfut.tx.controller;

import com.jiehfut.tx.service.BookService;
import com.jiehfut.tx.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * ClassName: CheckoutController
 * Package: com.jiehfut.tx.controller
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/12/2 22:12
 * @Version 1.0
 */
@Controller
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    // 一次性买多本书
    public void buyBook(Integer[] bookId, Integer userId) {
        // 调用 service 层方法
        checkoutService.checkout(bookId, userId);
    }

}
