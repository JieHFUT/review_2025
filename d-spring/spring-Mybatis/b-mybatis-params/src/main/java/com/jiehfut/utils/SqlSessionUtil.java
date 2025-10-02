package com.jiehfut.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 简单封装工具类获取 SqlSession 对象
 */
public class SqlSessionUtil {

    public static SqlSession getSqlSession() {
        SqlSession session = null;
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
            session = factory.openSession(true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return session;
    }


}
