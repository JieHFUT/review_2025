package com.jiehfut.headline.dao.impl;

import com.jiehfut.headline.dao.BaseDao;
import com.jiehfut.headline.dao.NewsHeadLineDao;
import com.jiehfut.headline.dao.NewsTypeDao;
import com.jiehfut.headline.pojo.HeadlinePageVo;
import com.jiehfut.headline.pojo.HeadlineQueryVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ClassName: NewsTypeDaoImpl
 * Package: com.jiehfut.headline.dao.impl
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/12/22 20:14
 * @Version 1.0
 */
public class NewsHeadlineDaoImpl extends BaseDao implements NewsHeadLineDao {

    @Override
    public List<HeadlinePageVo> findPageList(HeadlineQueryVo headlineQueryVo) {
        // 根据新闻类型 type，查询关键字 keywords，
        List params = new ArrayList();
        String sql = "select hid, title, type, page_views pageViews, TIMESTAMPDIFF(HOUR, create_time, now()) pastHours, publisher from news_headline where is_deleted = 0 ";
        if (headlineQueryVo.getType() != 0) {
            sql = sql.concat(" and type = ? ");
            params.add(headlineQueryVo);
        }
        if (headlineQueryVo.getKeyWords() != null && !headlineQueryVo.getKeyWords().equals("")) {
            sql = sql.concat(" and title like ? ");
            params.add("%" + headlineQueryVo.getKeyWords() + "%");
        }
        sql = sql.concat(" order by pastHours ASC, page_views DESC ");
        sql = sql.concat(" limit ?,? ");
        // 页码减1×页面大小 = 从哪里开始查
        params.add((headlineQueryVo.getPageNum() - 1) * headlineQueryVo.getPageSize());
        params.add(headlineQueryVo.getPageSize());

        return baseQuery(HeadlineQueryVo.class, sql, params.toArray());
    }

    @Override
    public int findPageCount(HeadlineQueryVo headlineQueryVo) {
        // 根据新闻类型 type，查询关键字 keywords，
        List params = new ArrayList();
        String sql = " select count(1) from news_headline where is_deleted = 0 ";
        if (headlineQueryVo.getType() != 0) {
            sql = sql.concat(" and type = ? ");
            params.add(headlineQueryVo);
        }
        if (headlineQueryVo.getKeyWords() != null && !headlineQueryVo.getKeyWords().equals("")) {
            sql = sql.concat(" and title like ? ");
            params.add("%" + headlineQueryVo.getKeyWords() + "%");
        }
        return baseQueryObject(Long.class, sql, params.toArray()).intValue();
    }
}
