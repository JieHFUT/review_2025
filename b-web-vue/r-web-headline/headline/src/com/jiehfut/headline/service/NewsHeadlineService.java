package com.jiehfut.headline.service;

import com.jiehfut.headline.pojo.HeadlineQueryVo;

import java.util.Map;

/**
 * ClassName: NewsUserService
 * Package: com.jiehfut.headline.service
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/12/22 20:16
 * @Version 1.0
 */
public interface NewsHeadlineService {
    /**
     * 用户登陆跳转的到首页后，需要查询页码信息，根据页码查询头条信息
     * @param headlineQueryVo
     * @return map
     * 返回的是所有的头条信息 map
     * pageInfo 种包含以下信息
     * "pageData":所有的头条信息数组
     * "pageNum":1,    //页码数
     * "pageSize":10,  // 页大小
     * "totalPage":20, // 总页数
     * "totalSize":200 // 总记录数
     */
    Map findPage(HeadlineQueryVo headlineQueryVo);
}
