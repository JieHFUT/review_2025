package com.jiehfut.headline.service;

import com.jiehfut.headline.pojo.NewsType;
import com.jiehfut.headline.pojo.NewsUser;

import java.util.List;

/**
 * ClassName: NewsUserService
 * Package: com.jiehfut.headline.service
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/12/22 20:16
 * @Version 1.0
 */
public interface NewsTypeService {
    /**
     * 查询所有的头条类型的信息响应给客户端
     * @return 多个头条类型的对象集合 List<NewsType>
     */
    List<NewsType> findAll();


}
