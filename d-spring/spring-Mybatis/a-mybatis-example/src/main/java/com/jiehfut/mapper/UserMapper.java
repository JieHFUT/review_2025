package com.jiehfut.mapper;


import com.jiehfut.pojo.User;

import java.util.List;

/**
 * UserMapper
 * mybatis 面向接口编程
 */
public interface UserMapper {
    /**
     * 添加用户信息
     */
    int insertUser();

    /**
     * 修改用户数据信息
     */
    void updateUser();

    /**
     * 删除用户信息
     */
    void deleteUser();

    /**
     * 查询结果为一条实体类对象
     */
    User selectUserById(int id);
    /**
     * 查询所有数据集合
     */
    List<User> selectAllUser();

}
