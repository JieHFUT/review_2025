package com.jiehfut;


import com.jiehfut.mapper.SQLMapper;
import com.jiehfut.pojo.User;
import com.jiehfut.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SQLMapperTest {
    
    @Test
    public void testUserByLike(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SQLMapper sqlMapper = sqlSession.getMapper(SQLMapper.class);
        List<User> users = sqlMapper.getUsersByLike("wang");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testDeleteMore(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SQLMapper sqlMapper = sqlSession.getMapper(SQLMapper.class);
        int i = sqlMapper.deleteMore("1,2,3");
        System.out.println("i = " + i);
    }


    // 动态获取表名
    @Test
    public void testGetUserByTableName(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SQLMapper sqlMapper = sqlSession.getMapper(SQLMapper.class);
        List<User> tUser = sqlMapper.getUserByTableName("t_user");
        for (User user : tUser) {
            System.out.println(user);
        }
    }


    /**
     * 获取添加记录的主键
     * void insertUser(User user)
     * useGeneratedKeys：表示允许获取主键，设置当前的 sql 中使用了自增的主键
     * keyProperty：不能把主键作为返回值，所以把主键数据放在 user 的 id 属性中（将自增的主键的值赋值给映射文件中参数的某个属性）
     */
    @Test
    public void testInsertUser(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SQLMapper sqlMapper = sqlSession.getMapper(SQLMapper.class);
        User user = new User(null, "zhaoliu", "473856", 23, "男", "34612789@qq.com");
        sqlMapper.insertUser(user);
        // 将添加该条记录的主键值放在 user 对象的 ID 中
        System.out.println(user);
        // User{id=8, username='zhaoliu', password='473856', age=23, sex='男', email='34612789@qq.com'}
    }








}
