package conm.jiehfut;

import com.jiehfut.mapper.DynamicSQLMapper;
import com.jiehfut.pojo.Emp;
import com.jiehfut.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DynamicSQLTest {
    @Test
    public void testGetEmpByCondition() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        DynamicSQLMapper dynamicSQLMapper = sqlSession.getMapper(DynamicSQLMapper.class);
        Emp emp = new Emp(null, "", 32, "", "234y12@qq.com", null);
        List<Emp> empList = dynamicSQLMapper.getEmpByCondition(emp);
        for (Emp emp1 : empList) {
            System.out.println(emp1);
        }
    }

    @Test
    // 使用 choose when otherwise
    public void testGetEmpByChoose() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        DynamicSQLMapper dynamicSQLMapper = sqlSession.getMapper(DynamicSQLMapper.class);
        Emp emp = new Emp(null, "", 32, "男", "234y12@qq.com", null);
        List<Emp> empList = dynamicSQLMapper.getEmpByChoose(emp);
        for (Emp emp1 : empList) {
            System.out.println(emp1);
        }
        // select * from t_emp WHERE age = ?
    }



    @Test
    // 使用数组进行批量删除
    public void testMoreByArray(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        DynamicSQLMapper dynamicSQLMapper = sqlSession.getMapper(DynamicSQLMapper.class);
        int i = dynamicSQLMapper.deleteMoreByArray(new Integer[]{6, 7, 9, 10, 11});
        System.out.println("i = " + i);
    }



    @Test
    public void testInsertMoreByList(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        DynamicSQLMapper dynamicSQLMapper = sqlSession.getMapper(DynamicSQLMapper.class);
        Emp emp = new Emp(null, "ihswioq", 52, "", "234y12qr@qq.com", null);
        Emp emp1 = new Emp(null, "ewqv", 322, "", "234y12@qq.com", null);
        Emp emp2 = new Emp(null, "cwqttcw", 3223, "", "2344ty12@qq.com", null);
        Emp emp3 = new Emp(null, "cewtqyhh5erh", 324, "", "24t34y12@qq.com", null);
        List<Emp> emps = Arrays.asList(emp, emp1, emp2, emp3);
        int i = dynamicSQLMapper.insertMoreByList(emps);
        System.out.println("i = " + i);
    }








}
