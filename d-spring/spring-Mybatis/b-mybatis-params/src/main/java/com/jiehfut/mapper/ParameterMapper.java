package com.jiehfut.mapper;

import com.jiehfut.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ParameterMapper {

    /**
     * 查询所有的员工信息集合2
     */
    List<User> getAllUsers();
    /**
     * 根据用户名查询用户信息
     */
    User getUserByName(String username);
    /**
     * 验证登陆，返回值是 User
     */
    User checkLogin(String username, String password);
    /**
     * 验证登陆，但是参数是 map 集合
     */
    User checkLoginByMap(Map<String, Object> map);
    /**
     * 添加用户信息
     */
    int addUser(User user);
    /**
     * 验证登陆，使用 @Param，自定义 mybatis 在将参数存储在 map 中的键的名称
     */
    User checkLoginByParam(@Param("useranme") String username, @Param("password") String password);
}
