package com.jiehfut.dssmtransaction.dao;

import com.jiehfut.dssmtransaction.bean.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class BookDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 按照图书 id 查询图书
     * @param id
     * @return
     */
    public Book getBookById(Integer id) {
        // 1.构建 sql 语句
        String sql = "select * from book where id = ?";
        // 2.执行 sql
        Book book = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Book.class), id);
        // 3.返回结果
        return book;
    }

    /**
     * 添加图书记录
     * @param book
     */
    public void insertBook(Book book) {
        String sql = "insert into book(bookName, price, stock) values(?,?,?)";
        int update = jdbcTemplate.update(sql, book.getBookName(), book.getPrice(), book.getStock());
    }

    /**
     * 减去库存
     * @param id
     * @param divStock
     * @return
     */
    public int updateBook(Integer id, Integer divStock) {
        String sql = "update book set stock = stock - ? where id = ?";
        int update = jdbcTemplate.update(sql, divStock, id);
        return update;
    }


    public int deleteBook(Integer id) {
        String sql = "delete from book where id = ?";
        int update = jdbcTemplate.update(sql, id);
        return update;
    }

}
