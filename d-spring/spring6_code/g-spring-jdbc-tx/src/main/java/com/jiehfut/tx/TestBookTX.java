package com.jiehfut.tx;

import com.jiehfut.tx.controller.BookController;
import com.jiehfut.tx.controller.CheckoutController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * ClassName: TestBookTX
 * Package: com.jiehfut.tx
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/12/2 21:08
 * @Version 1.0
 */
@Component
@SpringJUnitConfig(locations = "classpath:bean-jdbc.xml")
public class TestBookTX {

    @Autowired
    private BookController bookController;

    @Autowired
    private CheckoutController checkoutController;

    @Test
    public void testBuyBook(){
        bookController.buyBook(2, 1);
    }


    @Test
    public void testCheckoutBooks(){
        Integer[] bookIds = {1, 2};
        checkoutController.buyBook(bookIds, 1);
    }

}
