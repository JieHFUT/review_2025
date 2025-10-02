package com.jiehfut.mapper;

import com.jiehfut.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpMapper {
    /**
     * 查询所有的员工数据
     */
    List<Emp> findAllEmp();


    /**
     * 查询员工以及员工所对应的员工的部门信息
     */
    Emp getEmpAndDeptById(@Param("eid") Integer eid);

    /**
     * 处理多对一的映射关系
     * 进行分步查询，分步对两张表进行员工信息查询，部门信息查询
     * 第一步：查询员工信息：在 EmpMapper 中
     * 第二步：查询部门信息：在 DeptMapper 中
     */
    Emp getEmpAndDeptByStepOne(@Param("eid") Integer eid);



    /**
     * 分步查询
     * 第一步：在 Dept 中查询部门信息
     * 第二步：在 Emp 中根据 did 查询员工信息
     */
    List<Emp> getDeptAndEmpByStepTwo(@Param("did") Integer did);

}
