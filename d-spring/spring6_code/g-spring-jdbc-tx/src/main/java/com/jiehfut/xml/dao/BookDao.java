package com.jiehfut.xml.dao;

/**
 * ClassName: BookDao
 * Package: com.jiehfut.tx.dao
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/12/2 20:44
 * @Version 1.0
 */
public interface BookDao {

    /**
     * 根据书籍的 id 获取该书籍的价格
     * @param bookId
     * @return
     */
    Integer getBookPriceByBookId(Integer bookId);

    /**
     * 根据书籍的 id 来更新书籍的库存
     * @param bookId
     */
    void updateStock(Integer bookId);

    /**
     * 更新用户的账户
     * @param userId
     * @param price
     */
    void updateUserBalance(Integer userId, Integer price);
}
