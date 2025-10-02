package com.jiehfut;

import com.jiehfut.mapper.DeptMapper;
import com.jiehfut.mapper.EmpMapper;
import com.jiehfut.pojo.Dept;
import com.jiehfut.pojo.Emp;
import com.jiehfut.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ResultMapTest {

    @Test
    public void testAllEmp(){
        // 找不到对应属性的字段，不会为其赋值
        SqlSession session = SqlSessionUtil.getSqlSession();
        EmpMapper empMapper = session.getMapper(EmpMapper.class);
        List<Emp> empList = empMapper.findAllEmp();
        for (Emp emp : empList) {
            System.out.println(emp);
        }
    }

    @Test
    public void testEmpAndDeptResultMapOne(){
        // 找不到对应属性的字段，不会为其赋值
        SqlSession session = SqlSessionUtil.getSqlSession();
        EmpMapper empMapper = session.getMapper(EmpMapper.class);
        Emp emp = empMapper.getEmpAndDeptById(2);
        System.out.println(emp);
        /**
         * 使用级联属性进行多对一的处理
         * Emp(eid=2, empName=李四, age=32, sex=男, email=42529@163.com, dept=Dept(did=2, deptName=测试))
         */
    }

    @Test
    public void testGetEmpAndDeptByStepOne(){
        // 找不到对应属性的字段，不会为其赋值
        SqlSession session = SqlSessionUtil.getSqlSession();
        EmpMapper empMapper = session.getMapper(EmpMapper.class);
        Emp emp = empMapper.getEmpAndDeptByStepOne(2);
        System.out.println(emp);
        // Emp(eid=2, empName=李四, age=32, sex=男, email=42529@163.com, dept=Dept(did=2, deptName=测试))
    }












    // 一对多 联表查询
    @Test
    public void testGetDeptAndEmp(){
        SqlSession session = SqlSessionUtil.getSqlSession();
        DeptMapper deptMapper = session.getMapper(DeptMapper.class);
        Dept dept = deptMapper.getDeptAndEmp(2);
        System.out.println(dept);
        // Dept(did=2, deptName=测试, empList=[
        // Emp(eid=2, empName=李四, age=32, sex=男, email=42529@163.com, dept=null),
        // Emp(eid=5, empName=田七, age=32, sex=男, email=234@gmail, dept=null)])
    }


    //一对多 分步查询
    @Test
    public void testGetDeptAndEmpByStep() {
        SqlSession session = SqlSessionUtil.getSqlSession();
        DeptMapper deptMapper = session.getMapper(DeptMapper.class);
        Dept dept = deptMapper.getDeptAndDeptByStepOne(2);
        System.out.println(dept);
        // Dept(did=null, deptName=测试, empList=[
        // Emp(eid=2, empName=李四, age=32, sex=男, email=42529@163.com, dept=null),
        // Emp(eid=5, empName=田七, age=32, sex=男, email=234@gmail, dept=null)])
    }







}
