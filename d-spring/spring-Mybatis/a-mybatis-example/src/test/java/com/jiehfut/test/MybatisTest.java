package com.jiehfut.test;

import com.jiehfut.mapper.UserMapper;
import com.jiehfut.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class MybatisTest {
    @Test
    public void testMybatis() throws IOException {
        // mybatis 为我们提供了一个 sqlsession 对象用于操作

        // 1.加载核心配置文件（以字节输入流获取）
        InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
        // 2.获取 SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        // 3.获取 SqlSessionFactory 对象
        SqlSessionFactory factory = builder.build(stream);
        // 4.获取 sqlsession 对象（代表 Java 程序和数据库之间的会话）
        SqlSession sqlSession = factory.openSession();

        // 5.我们传进去一个类的类型的时候，就返回一个该类的对象，可以帮助我们返回一个接口的实现类的对象，使用的是代理模式
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 测试功能
        int result = userMapper.insertUser();
        System.out.println("result = " + result);

        // 提交事务
        sqlSession.commit();
    }


    /**
     * SqlSession 默认不会自动提交事务，可以通过设置让其自动提交事务
     * @throws IOException
     */
    @Test
    public void testMybatisCommit() throws IOException {
        // mybatis 为我们提供了一个 sqlsession 对象用于操作

        // 1.加载核心配置文件（以字节输入流获取）
        InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
        // 2.获取 SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        // 3.获取 SqlSessionFactory 对象
        SqlSessionFactory factory = builder.build(stream);
        // 4.获取 sqlsession 对象（代表 Java 程序和数据库之间的会话）
        // mybatis 不会像 jdbc 一样自动提交事务，可以通过设置 true 让其自动提交事务
        SqlSession sqlSession = factory.openSession(true);

        // 5.我们传进去一个类的类型的时候，就返回一个该类的对象，可以帮助我们返回一个接口的实现类的对象，使用的是代理模式
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 测试功能
        int result = userMapper.insertUser();
        System.out.println("result = " + result);
    }


    @Test
    public void testUpdate() throws IOException {
        // mybatis 为我们提供了一个 sqlsession 对象用于操作

        // 1.加载核心配置文件（以字节输入流获取）
        InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
        // 2.获取 SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        // 3.获取 SqlSessionFactory 对象
        SqlSessionFactory factory = builder.build(stream);
        // 4.获取 sqlsession 对象（代表 Java 程序和数据库之间的会话）
        // mybatis 不会像 jdbc 一样自动提交事务，可以通过设置 true 让其自动提交事务
        SqlSession sqlSession = factory.openSession(true);

        // 5.我们传进去一个类的类型的时候，就返回一个该类的对象，可以帮助我们返回一个接口的实现类的对象，使用的是代理模式
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.updateUser();

    }

    @Test
    public void testDelete() throws IOException {
        // mybatis 为我们提供了一个 sqlsession 对象用于操作

        // 1.加载核心配置文件（以字节输入流获取）
        InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
        // 2.获取 SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        // 3.获取 SqlSessionFactory 对象
        SqlSessionFactory factory = builder.build(stream);
        // 4.获取 sqlsession 对象（代表 Java 程序和数据库之间的会话）
        // mybatis 不会像 jdbc 一样自动提交事务，可以通过设置 true 让其自动提交事务
        SqlSession sqlSession = factory.openSession(true);

        // 5.我们传进去一个类的类型的时候，就返回一个该类的对象，可以帮助我们返回一个接口的实现类的对象，使用的是代理模式
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.deleteUser();
    }

    @Test
    public void testSelectById() throws IOException {
        // mybatis 为我们提供了一个 sqlsession 对象用于操作

        // 1.加载核心配置文件（以字节输入流获取）
        InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
        // 2.获取 SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        // 3.获取 SqlSessionFactory 对象
        SqlSessionFactory factory = builder.build(stream);
        // 4.获取 sqlsession 对象（代表 Java 程序和数据库之间的会话）
        // mybatis 不会像 jdbc 一样自动提交事务，可以通过设置 true 让其自动提交事务
        SqlSession sqlSession = factory.openSession(true);

        // 5.我们传进去一个类的类型的时候，就返回一个该类的对象，可以帮助我们返回一个接口的实现类的对象，使用的是代理模式
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectUserById(2);
        System.out.println(user);
    }

    @Test
    public void testSelectAllUser() throws IOException {
        // mybatis 为我们提供了一个 sqlsession 对象用于操作

        // 1.加载核心配置文件（以字节输入流获取）
        InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
        // 2.获取 SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        // 3.获取 SqlSessionFactory 对象
        SqlSessionFactory factory = builder.build(stream);
        // 4.获取 sqlsession 对象（代表 Java 程序和数据库之间的会话）
        // mybatis 不会像 jdbc 一样自动提交事务，可以通过设置 true 让其自动提交事务
        SqlSession sqlSession = factory.openSession(true);

        // 5.我们传进去一个类的类型的时候，就返回一个该类的对象，可以帮助我们返回一个接口的实现类的对象，使用的是代理模式
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.selectAllUser();
        for (User user : users) {
            System.out.println(user);
        }

    }







}
