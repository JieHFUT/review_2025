package com.jiehfut.xml.service;

/**
 * ClassName: BookService
 * Package: com.jiehfut.tx.service
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/12/2 20:44
 * @Version 1.0
 */
public interface BookService {
    /**
     * 买书的方法
     * @param bookId
     * @param userId
     */
    void buyBook(Integer bookId, Integer userId);

}
