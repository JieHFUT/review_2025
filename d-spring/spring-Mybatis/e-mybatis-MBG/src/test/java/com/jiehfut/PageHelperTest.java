package com.jiehfut;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class PageHelperTest {

    @Test
    // limit index, pageSize
    // index：当前页面的索引
    // pageSize：每一页的条数
    // pageNum：当前页的页码  index = (pageNum-1)*pageSize
    public void testPageHelper() throws IOException {
        InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(stream);
        SqlSession session = factory.openSession();
        
    }
}
