package com.jiehfut;

import com.jiehfut.mapper.SelectMapper;
import com.jiehfut.pojo.User;
import com.jiehfut.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;

public class SelectMapperTest {


    @Test
    public void testGetUserById(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SelectMapper selectMapper = sqlSession.getMapper(SelectMapper.class);
        User user = selectMapper.getUserById(4);
        System.out.println(user);
    }


    @Test
    public void testGetAllUser(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SelectMapper selectMapper = sqlSession.getMapper(SelectMapper.class);
        List<User> userList = selectMapper.getAllUser();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void testGetUserCount(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SelectMapper selectMapper = sqlSession.getMapper(SelectMapper.class);
        int count = selectMapper.getUserCount();
        System.out.println("count = " + count);
    }



    @Test
    public void testGetUserByIdToMap(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SelectMapper selectMapper = sqlSession.getMapper(SelectMapper.class);
        Map<String, Object> userByIdToMap = selectMapper.getUserByIdToMap(4);
        System.out.println(userByIdToMap);
        // 将字段名作为 map 的键，以字段值作为 map 的值
        // {password=1234124, sex=女, id=4, age=22, email=2431234@163.com, username=张三}
    }


    @Test
    public void testGetAllUserToMap(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SelectMapper selectMapper = sqlSession.getMapper(SelectMapper.class);
        Map<String, Object> map = selectMapper.getAllUserToMap();
        System.out.println(map);
        // 使用注解以 ID 为键
        // {3={password=123456, sex=男, id=3, age=23, email=32425987@qq.com, username=admin},
        //  4={password=1234124, sex=女, id=4, age=22, email=2431234@163.com, username=张三},
        //  5={password=327452, sex=男, id=5, age=13, email=275433219@gmail.com, username=李四}}
    }


    



}
