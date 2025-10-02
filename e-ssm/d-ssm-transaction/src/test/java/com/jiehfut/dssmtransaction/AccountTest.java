package com.jiehfut.dssmtransaction;


import com.jiehfut.dssmtransaction.dao.AccountDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class AccountTest {

    @Autowired
    AccountDao accountDao;

    @Test
    public void testAccountDiv(){
        accountDao.updateByUsername("zhangsan", new BigDecimal(100));
    }
}
