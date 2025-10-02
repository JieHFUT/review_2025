package com.jiehfut.springmybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehfut.springmybatisplus.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;


@Repository
/**
 * 这个泛型<User>决定了从哪个表里面进行 sql 操作
 * 可以使用在实体类添加注解设置实体类对应的表名
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 自定义根据 ID 来查询一条用户记录，返回一个 Map 集合
     * @param id
     * @return
     */
    Map<String, Object> selectMapById(@Param("id") Long id);


    /**
     * 自定义根据年龄来查询记录，然后分页
     * @param page mybatis-plus 所提供的分页对象，必需位于第一个参数
     * @param age
     * @return
     */
    Page<User> selectPageVo(@Param("page") Page<User> page, @Param("age") Integer age);

}
