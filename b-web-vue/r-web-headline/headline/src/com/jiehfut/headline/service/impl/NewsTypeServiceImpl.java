package com.jiehfut.headline.service.impl;

import com.jiehfut.headline.dao.NewsTypeDao;
import com.jiehfut.headline.dao.impl.NewsTypeDaoImpl;
import com.jiehfut.headline.pojo.NewsType;
import com.jiehfut.headline.service.NewsTypeService;

import java.util.List;

/**
 * ClassName: NewsUserServiceImpl
 * Package: com.jiehfut.headline.service.impl
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/12/22 20:17
 * @Version 1.0
 */
public class NewsTypeServiceImpl implements NewsTypeService {

    private NewsTypeDao newsTypeDao = new NewsTypeDaoImpl();

    @Override
    public List<NewsType> findAll() {
        return newsTypeDao.findAll();
    }
}
