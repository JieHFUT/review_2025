package com.jiehfut.dssmtransaction.service;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface UserService {

    /**
     * 用户买书
     * @param username
     * @param bookId
     * @param buyNum
     */
    void checkout(String username, Integer bookId, Integer buyNum) throws IOException;


    /**
     * 使用事务传播行为：NESTED（基于保存点）
     * 实现用户结账，触发回滚 => 账户金钱减少（子事务不会滚），库存不变（子事务回滚）
     * @param username
     * @param bookId
     * @param buyNum
     */
    public void nested(String username, Integer bookId, Integer buyNum);
}
