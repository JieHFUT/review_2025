package com.jiehfut.headline.dao;

import com.jiehfut.headline.pojo.NewsType;

import java.util.List;

/**
 * ClassName: NewsUserDao
 * Package: com.jiehfut.headline.dao
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/12/22 20:12
 * @Version 1.0
 */
public interface NewsTypeDao {


    /**
     * 查询所有的头条类型的信息响应给客户端
     * @return 多个头条类型的对象集合 List<NewsType>
     */
    List<NewsType> findAll();
}
