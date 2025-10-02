package com.jiehfut.mapper;

import com.jiehfut.pojo.Emp;
import org.apache.ibatis.annotations.Param;

public interface CacheMapper {
    /**
     * 测试一级缓存
     */
    Emp getEmpById(@Param("eid")Integer eid);
    /**
     * 添加操作
     */
    void insertEmp(Emp emp);




}
