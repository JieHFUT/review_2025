package com.jiehfut.headline.dao;

import com.jiehfut.headline.pojo.HeadlinePageVo;
import com.jiehfut.headline.pojo.HeadlineQueryVo;

import java.util.List;
import java.util.Map;

/**
 * ClassName: NewsUserDao
 * Package: com.jiehfut.headline.dao
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/12/22 20:12
 * @Version 1.0
 */
public interface NewsHeadLineDao {

    /**
     * 返回页大小的头条信息
     * "hid":"1",                     // 新闻id
     * "title":"宣布 ... ...",   // 新闻标题
     * "type":"1",                    // 新闻所属类别编号
     * "pageViews":"40",              // 新闻浏览量
     * "pastHours":"3" ,              // 发布时间已过小时数
     * "publisher":"1"                // 发布用户ID
     * @param headlineQueryVo
     * @return
     */
    List<HeadlinePageVo> findPageList(HeadlineQueryVo headlineQueryVo);


    /**
     *
     * @param headlineQueryVo
     * @return
     */
    int findPageCount(HeadlineQueryVo headlineQueryVo);
}
