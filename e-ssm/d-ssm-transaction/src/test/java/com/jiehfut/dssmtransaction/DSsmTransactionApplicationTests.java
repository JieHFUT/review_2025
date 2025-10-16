package com.jiehfut.dssmtransaction;

import com.jiehfut.dssmtransaction.bean.Book;
import com.jiehfut.dssmtransaction.dao.AccountDao;
import com.jiehfut.dssmtransaction.dao.BookDao;
import com.jiehfut.dssmtransaction.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class DSsmTransactionApplicationTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate; // 就是 QueryRunner

    @Autowired
    BookDao bookDao;

    @Autowired
    UserService userService;
    @Autowired
    private AccountDao accountDao;

    @Test
    void contextLoads() throws SQLException {
        // 测试数据连接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        // HikariProxyConnection@267769073 wrapping com.mysql.cj.jdbc.ConnectionImpl@28ce75ec

    }

    @Test
    public void testQueryBookById(){
        Book bookById = bookDao.getBookById(1);
        System.out.println(bookById);
        // Book(id=1, bookName=剑指Java, price=100.00, stock=100)
    }

    @Test
    public void testInsertBook(){
        Book book = new Book(null, "西游记", new BigDecimal(100), 100);
        bookDao.insertBook(book);
    }

    @Test
    public void testUpdateBook(){
        int i = bookDao.updateBook(4, 2);
        System.out.println(i);
    }

    @Test
    public void testDelete(){
        int i = bookDao.deleteBook(4);
        System.out.println(i);
    }

    
    @Test
    public void testCheckout() throws IOException {
        // 买书结账
        userService.checkout("zhangsan", 1, 10);
    }

    


}
