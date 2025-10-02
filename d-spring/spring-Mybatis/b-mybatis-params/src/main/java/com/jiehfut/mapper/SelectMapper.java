package com.jiehfut.mapper;

import com.jiehfut.pojo.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SelectMapper {

    /**
     * 根据 ID 来查询用户信息
     */
    User getUserById(@Param("id") Integer id);
    /**
     * 查询所有用户信息
     */
    List<User> getAllUser();
    /**
     * 查询所有用户的数量
     */
    Integer getUserCount();

    /**
     * 根据 id 查询用户信息为 map 的一个集合
     */
    Map<String, Object> getUserByIdToMap(@Param("id") Integer id);
    /**
     * 查询所有用户信息为 map 的一个集合
     * 使用注解以 ID 为键
     * {3={password=123456, sex=男, id=3, age=23, email=32425987@qq.com, username=admin},
     *  4={password=1234124, sex=女, id=4, age=22, email=2431234@163.com, username=张三},
     *  5={password=327452, sex=男, id=5, age=13, email=275433219@gmail.com, username=李四}}
     */
    @MapKey("id") // 将 ID 作为键
    Map<String, Object> getAllUserToMap();

}
