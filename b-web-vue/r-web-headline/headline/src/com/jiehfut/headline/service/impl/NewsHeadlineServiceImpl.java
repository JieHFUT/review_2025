package com.jiehfut.headline.service.impl;

import com.jiehfut.headline.dao.NewsHeadLineDao;
import com.jiehfut.headline.dao.impl.NewsHeadlineDaoImpl;
import com.jiehfut.headline.pojo.HeadlinePageVo;
import com.jiehfut.headline.pojo.HeadlineQueryVo;
import com.jiehfut.headline.pojo.NewsHeadline;
import com.jiehfut.headline.service.NewsHeadlineService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: NewsUserServiceImpl
 * Package: com.jiehfut.headline.service.impl
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/12/22 20:17
 * @Version 1.0
 */
public class NewsHeadlineServiceImpl implements NewsHeadlineService {
    NewsHeadLineDao newsHeadlineDao = new NewsHeadlineDaoImpl();

    @Override
    public Map findPage(HeadlineQueryVo headlineQueryVo) {
        // 页码数
        int pageNum = headlineQueryVo.getPageNum();
        // 页大小
        int pageSize = headlineQueryVo.getPageSize();
        // 新闻内容，查询的条数为页大小
        List<HeadlinePageVo> pageData = newsHeadlineDao.findPageList(headlineQueryVo);
        // 获取瞒住查询条件的信息一共有多少条
        int totalSize = newsHeadlineDao.findPageCount(headlineQueryVo);
        // 总的页码数目
        int totalPage = totalSize / pageSize == 0 ? totalSize % pageSize : totalSize / pageSize + 1;
        Map pageInfo = new HashMap();
        pageInfo.put("pageNum", pageNum);
        pageInfo.put("pageSize", pageSize);
        pageInfo.put("totalSize", totalSize);
        pageInfo.put("totalPage", totalPage);
        return pageInfo;
    }
}
