package com.jiehfut.tx.service.impl;

import com.jiehfut.tx.service.BookService;
import com.jiehfut.tx.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * ClassName: CheckoutServiceImpl
 * Package: com.jiehfut.tx.service.impl
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/12/2 22:08
 * @Version 1.0
 */
@Service
public class CheckoutServiceImpl implements CheckoutService {

    @Autowired
    private BookService bookService;


    /**
     * 一次性买多本书的方法
     * 用来演示事务的传播行为
     * REQUIRED：支持当前事务，如果不存在就新建一个
     * 这种行为也是默认的事务传播行为
     * @param bookId
     * @param userId
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void checkout(Integer[] bookId, Integer userId) {
        for (Integer bookId1 : bookId) {
            bookService.buyBook(bookId1, userId);
        }
    }
}
