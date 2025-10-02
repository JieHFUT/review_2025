package com.jiehfut;

import com.jiehfut.mapper.ParameterMapper;
import com.jiehfut.pojo.User;
import com.jiehfut.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParameterMapperTest {

    @Test
    public void testGetAllUser(){
        // 简单封装工具类获取 SqlSession
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        ParameterMapper parameterMapper = sqlSession.getMapper(ParameterMapper.class);
        List<User> userList = parameterMapper.getAllUsers();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    /**
     * mybatis 获取参数的两种方法
     * ${} 本质是字符串拼接，可能会出现字符注入的问题
     * #{} 本质是占位符赋值
     *
     * mybatis 获取参数值的各种情况
     * 1.mapper 接口的参数为单个的字面量的类型
     *   可以通过 ${} #{} 以任意的字符串来获取参数值，但是要注意 '${}' 的单引号问题
     * 2.mapper 接口的参数为多个
     *   Available parameters are [arg1, arg0, param1, param2]
     *   有多个参数的时候，mybatis 检测到会自动将参数放进 map 集合中，键可以混着用
     * 3.手动将 mapper 接口中的多个参数放在一个 map 中去存储
     * 4.如果 mapper 方法的参数是一个实体类的参数（属性：属性值）
     *   只要以 #{} 的方式直接填写属性名即可，但是要注意 '${}' 的单引号问题
     * 5.命名参数，使用注解 @Param 的值来命名参数
     *   此时自定义 mybatis 在将参数存储在 map 中的键的名称
     *   可以使用自己命名的参数名称，也可以使用 param1，param2
     *
     *
     */

    @Test
    public void testGetUserByUsername(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        ParameterMapper parameterMapper = sqlSession.getMapper(ParameterMapper.class);
        User user = parameterMapper.getUserByName("admin");
        System.out.println(user);
    }

    @Test
    public void testCheckLogin(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        ParameterMapper parameterMapper = sqlSession.getMapper(ParameterMapper.class);
        User user = parameterMapper.checkLogin("admin", "123456");
        System.out.println(user);
    }

    @Test
    public void testCheckLoginByMap(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        ParameterMapper parameterMapper = sqlSession.getMapper(ParameterMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("username", "admin");
        map.put("password", "123456");
        User user = parameterMapper.checkLoginByMap(map);
        System.out.println(user);
    }

    @Test
    public void testAddUser(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        ParameterMapper parameterMapper = sqlSession.getMapper(ParameterMapper.class);
        int i = parameterMapper.addUser(new User(null, "李四", "327452", 13, "男", "275433219@gmail.com"));
        System.out.println("i = " + i);
    }

    @Test
    public void testCheckLoginByParam(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        ParameterMapper parameterMapper = sqlSession.getMapper(ParameterMapper.class);
        User user = parameterMapper.checkLogin("admin", "123456");
        System.out.println(user);
    }









}
