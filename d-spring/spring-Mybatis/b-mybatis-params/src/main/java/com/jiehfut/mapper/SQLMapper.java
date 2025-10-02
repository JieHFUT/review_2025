package com.jiehfut.mapper;

import com.jiehfut.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SQLMapper {
    /**
     * 根据用户名字进行模糊查询
     */
    List<User> getUsersByLike(@Param("username") String username);

    /**
     * 进行批量删除
     */
    int deleteMore(@Param("ids") String ids);

    /**
     * 查询指定表中的数据，动态获取表名
     */
    List<User> getUserByTableName(@Param("tableName") String tableName);

    /**
     * 添加一条用户记录，获得添加的增主键
     */
    void insertUser(User user);

}