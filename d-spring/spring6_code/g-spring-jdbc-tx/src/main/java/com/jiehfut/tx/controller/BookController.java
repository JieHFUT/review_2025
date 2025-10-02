package com.jiehfut.tx.controller;

import com.jiehfut.tx.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * ClassName: BookController
 * Package: com.jiehfut.tx.controller
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/12/2 20:43
 * @Version 1.0
 */

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    public void buyBook(Integer bookId, Integer userId) {
        // 调用 service 层方法
        bookService.buyBook(bookId, userId);
    }

}
