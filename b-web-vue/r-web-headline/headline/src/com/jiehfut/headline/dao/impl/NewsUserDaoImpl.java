package com.jiehfut.headline.dao.impl;

import com.jiehfut.headline.dao.BaseDao;
import com.jiehfut.headline.dao.NewsUserDao;
import com.jiehfut.headline.pojo.NewsUser;

import java.sql.SQLOutput;
import java.util.List;

/**
 * ClassName: NewsUserDaoImpl
 * Package: com.jiehfut.headline.dao.impl
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/12/22 20:12
 * @Version 1.0
 */
public class NewsUserDaoImpl extends BaseDao implements NewsUserDao {

    @Override
    public NewsUser findByUsername(String username) {
        String sql = """
                select 
                    uid,
                    username,
                    user_pwd userPwd,
                    nick_name nickName
                from 
                    news_user 
                where 
                    username = ?
                """;
        List<NewsUser> newsUserList = baseQuery(NewsUser.class, sql, username);
        if(newsUserList != null && newsUserList.size() > 0){
            NewsUser newsUser = newsUserList.get(0);
            return newsUser;
        } else {
            return null;
        }
    }

    @Override
    public NewsUser findByuid(Integer uid) {
        String sql = """
                select 
                    uid,
                    username,
                    user_pwd userPwd,
                    nick_name nickName
                from 
                    news_user 
                where 
                    uid = ?
                """;
        List<NewsUser> newsUserList = baseQuery(NewsUser.class, sql, uid);
        if(newsUserList != null && newsUserList.size() > 0){
            NewsUser newsUser = newsUserList.get(0);
            return newsUser;
        } else {
            return null;
        }
    }

    @Override
    public Integer registUser(NewsUser newsUser) {
        String sql = "insert into news_user values (DEFAULT,?,?,?)";
        return baseUpdate(sql, newsUser.getUsername(), newsUser.getUserPwd(), newsUser.getNickName());
    }


}
