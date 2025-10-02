package com.jiehfut.mapper;

import com.jiehfut.pojo.Dept;
import org.apache.ibatis.annotations.Param;

public interface DeptMapper {

    /**
     * 处理多对一的映射关系
     * 进行分步查询，分步对两张表进行员工信息查询，部门信息查询
     * 第一步：查询员工信息：在 EmpMapper 中
     * 第二步：查询部门信息：在 DeptMapper 中，通过 did 查询员工对应的信息
     */
    Dept getEmpAndDeptByStepTwo(@Param("did") Integer did);


    /**
     * 部门一对多
     * 获取部门以及部门中所有的员工信息
     */
    Dept getDeptAndEmp(@Param("did") Integer did);

    /**
     * 分步查询
     * 第一步：在 Dept 中查询部门信息
     * 第二步：
     */
    Dept getDeptAndDeptByStepOne(@Param("did") Integer did);






}
