package com.jiehfut.mapper;

import com.jiehfut.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DynamicSQLMapper {

    /**
     * 根据多条件查询（查询条件可能是多条，可能是一条）
     */
    List<Emp> getEmpByCondition(Emp emp);
    /**
     * 测试 choose when otherwise
     */
    List<Emp> getEmpByChoose(Emp emp);
    /**
     * 使用数组实现批量删除
     */
    int deleteMoreByArray(@Param("eids") Integer[] eids);

    /**
     * 通过 List 集合进行批量添加
     */
    int insertMoreByList(@Param("emps") List<Emp> emps);
}
