package conm.jiehfut;

import com.jiehfut.mapper.CacheMapper;
import com.jiehfut.pojo.Emp;
import com.jiehfut.utils.SqlSessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class CacheMapperTest {

    @Test
    public void testCache(){
        SqlSession session = SqlSessionUtil.getSqlSession();
        CacheMapper cacheMapper = session.getMapper(CacheMapper.class);
        Emp emp1 = cacheMapper.getEmpById(2);
        System.out.println(emp1);
        Emp emp2 = cacheMapper.getEmpById(2);
        System.out.println(emp2);

        /**
         * DEBUG 12-12 17:20:59,544 ==>  Preparing: select * from t_emp where eid = ?  (BaseJdbcLogger.java:137)
         * DEBUG 12-12 17:20:59,589 ==> Parameters: 2(Integer)  (BaseJdbcLogger.java:137)
         * DEBUG 12-12 17:20:59,637 <==      Total: 1  (BaseJdbcLogger.java:137)
         * Emp(eid=2, empName=null, age=32, sex=男, email=42529@163.com, dept=null)
         * Emp(eid=2, empName=null, age=32, sex=男, email=42529@163.com, dept=null)
         *
         * 可见 mybatis 的一级缓存是默认开启的，级别是 SqlSession 的
         */
    }


    @Test
    public void testCacheNew(){
        SqlSession session1 = SqlSessionUtil.getSqlSession();
        CacheMapper cacheMapper = session1.getMapper(CacheMapper.class);
        Emp emp1 = cacheMapper.getEmpById(2);
        System.out.println(emp1);
        Emp emp2 = cacheMapper.getEmpById(2);
        System.out.println(emp2);

        SqlSession session2 = SqlSessionUtil.getSqlSession();
        CacheMapper cacheMapper2 = session2.getMapper(CacheMapper.class);
        Emp emp3 = cacheMapper2.getEmpById(2);
        System.out.println(emp3);

        /**
         * DEBUG 12-12 17:26:24,433 ==>  Preparing: select * from t_emp where eid = ?  (BaseJdbcLogger.java:137)
         * DEBUG 12-12 17:26:24,481 ==> Parameters: 2(Integer)  (BaseJdbcLogger.java:137)
         * DEBUG 12-12 17:26:24,528 <==      Total: 1  (BaseJdbcLogger.java:137)
         * Emp(eid=2, empName=null, age=32, sex=男, email=42529@163.com, dept=null)
         * Emp(eid=2, empName=null, age=32, sex=男, email=42529@163.com, dept=null)
         * DEBUG 12-12 17:26:24,632 ==>  Preparing: select * from t_emp where eid = ?  (BaseJdbcLogger.java:137)
         * DEBUG 12-12 17:26:24,633 ==> Parameters: 2(Integer)  (BaseJdbcLogger.java:137)
         * DEBUG 12-12 17:26:24,636 <==      Total: 1  (BaseJdbcLogger.java:137)
         * Emp(eid=2, empName=null, age=32, sex=男, email=42529@163.com, dept=null)
         *
         *
         * 可见 mybatis 的一级缓存是默认开启的，级别是 SqlSession 的
         * 当通过不同的 SqlSession 进行查询的时候，就会执行不同的查询
         */
    }

    /**
     * 使一级缓存失效的四种办法
     *
     * 1) 不同的SqlSession对应不同的一级缓存
     * 2) 同一个SqlSession但是查询条件不同
     * 3) 同一个SqlSession两次查询期间执行了任何一次增删改操作
     * 4) 同一个SqlSession两次查询期间手动清空了缓存
     *
     * 也可以使用 sqlSession.clearChace() 清空一级缓存
     */
    @Test
    public void testCacheInsert() {
        SqlSession session = SqlSessionUtil.getSqlSession();
        CacheMapper cacheMapper = session.getMapper(CacheMapper.class);
        Emp emp1 = cacheMapper.getEmpById(2);
        System.out.println(emp1);
        // 两次查询之间进行了插入
        Emp emp = new Emp(null, "dqwe", 21, "男", "3241@wef.com", null);
        cacheMapper.insertEmp(emp);
        // 一级缓存就会被清空
        Emp emp2 = cacheMapper.getEmpById(2);
        System.out.println(emp2);
        /**
         * DEBUG 12-12 17:37:46,220 ==>  Preparing: select * from t_emp where eid = ?  (BaseJdbcLogger.java:137)
         * DEBUG 12-12 17:37:46,268 ==> Parameters: 2(Integer)  (BaseJdbcLogger.java:137)
         * DEBUG 12-12 17:37:46,317 <==      Total: 1  (BaseJdbcLogger.java:137)
         * Emp(eid=2, empName=null, age=32, sex=男, email=42529@163.com, dept=null)
         * DEBUG 12-12 17:37:46,335 ==>  Preparing: insert into t_emp values (null, ?, ?, ?, ?, null)  (BaseJdbcLogger.java:137)
         * DEBUG 12-12 17:37:46,337 ==> Parameters: dqwe(String), 21(Integer), 男(String), 3241@wef.com(String)  (BaseJdbcLogger.java:137)
         * DEBUG 12-12 17:37:46,352 <==    Updates: 1  (BaseJdbcLogger.java:137)
         * DEBUG 12-12 17:37:46,353 ==>  Preparing: select * from t_emp where eid = ?  (BaseJdbcLogger.java:137)
         * DEBUG 12-12 17:37:46,353 ==> Parameters: 2(Integer)  (BaseJdbcLogger.java:137)
         * DEBUG 12-12 17:37:46,356 <==      Total: 1  (BaseJdbcLogger.java:137)
         * Emp(eid=2, empName=null, age=32, sex=男, email=42529@163.com, dept=null)
         *
         * 可见任意的一次增删改之后就会清空一级缓存
         */
    }




    /**
     * 下面是对二级缓存的测试
     *
     * a>在核心配置文件中，设置全局配置属性cacheEnabled="true"，默认为 true，不需要设置
     * b>在映射文件中设置标签 <cache/>
     * c>二级缓存必须在 SqlSession 关闭或提交之后有效
     * d>查询的数据所转换的实体类类型必须实现序列化的接口
     *
     *
     * 两次查询之间执行了任意的增删改，会使一级和二级缓存同时失效
     */
    @Test
    public void testTwoCache(){
        try {
            InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(stream);
            SqlSession session1 = factory.openSession(true);
            SqlSession session2 = factory.openSession(true);


            CacheMapper mapper1 = session1.getMapper(CacheMapper.class);
            CacheMapper mapper2 = session2.getMapper(CacheMapper.class);
            System.out.println(mapper1.getEmpById(2));
            session1.close();
            System.out.println(mapper2.getEmpById(2));
            session2.close();

            /**
             * DEBUG 12-12 18:00:50,241 Cache Hit Ratio [com.jiehfut.mapper.CacheMapper]: 0.0  (LoggingCache.java:60)
             * DEBUG 12-12 18:00:50,722 ==>  Preparing: select * from t_emp where eid = ?  (BaseJdbcLogger.java:137)
             * DEBUG 12-12 18:00:50,768 ==> Parameters: 2(Integer)  (BaseJdbcLogger.java:137)
             * DEBUG 12-12 18:00:50,814 <==      Total: 1  (BaseJdbcLogger.java:137)
             * Emp(eid=2, empName=null, age=32, sex=男, email=42529@163.com, dept=null)
             * WARN  12-12 18:00:50,851 As you are using functionality that deserializes object streams, it is recommended to define the JEP-290 serial filter. Please refer to https://docs.oracle.com/pls/topic/lookup?ctx=javase15&id=GUID-8296D8E8-2B93-4B9A-856E-0A65AF9B8C66  (SerialFilterChecker.java:46)
             * DEBUG 12-12 18:00:50,856 Cache Hit Ratio [com.jiehfut.mapper.CacheMapper]: 0.5  (LoggingCache.java:60)
             * Emp(eid=2, empName=null, age=32, sex=男, email=42529@163.com, dept=null)
             *
             * 可见在第一次查询后关闭 sqlsession 后，再次查询发现数据已经被放在二级缓存中
             */

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }









}
