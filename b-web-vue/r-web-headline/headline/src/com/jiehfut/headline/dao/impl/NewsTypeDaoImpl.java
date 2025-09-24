package com.jiehfut.headline.dao.impl;

import com.jiehfut.headline.dao.BaseDao;
import com.jiehfut.headline.dao.NewsTypeDao;
import com.jiehfut.headline.pojo.NewsType;

import java.util.List;

/**
 * ClassName: NewsTypeDaoImpl
 * Package: com.jiehfut.headline.dao.impl
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/12/22 20:14
 * @Version 1.0
 */
public class NewsTypeDaoImpl extends BaseDao implements NewsTypeDao {

    @Override
    public List<NewsType> findAll() {
        String sql = "select tid, tname from news_type";
        return baseQuery(NewsType.class, sql);
    }
}
