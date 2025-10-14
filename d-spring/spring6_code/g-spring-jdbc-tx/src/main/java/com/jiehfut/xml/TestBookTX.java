package com.jiehfut.xml;

import com.jiehfut.tx.controller.CheckoutController;
import com.jiehfut.xml.controller.BookController;
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
@SpringJUnitConfig(locations = "classpath:bean-jdbc-xml.xml")
public class TestBookTX {

    @Autowired
    private BookController bookController;


    @Test
    public void testBuyBook(){
        bookController.buyBook(2, 1);
    }


}
