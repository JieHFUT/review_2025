package jiehfut.hfut.schedule.dao;

import jiehfut.hfut.schedule.util.JdbcUtil;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: BaseDao
 * Package: jie.hfut.schedule.dao
 * Description:
 * 该 BaseDao 类封装了对数据库 CRUD 的具体操作方法
 * 其他的 Dao 类如果需要对数据库见操作，只需要传入参数，调用该封装类的接口即可
 * 1.向外提供一个公共的查询方法，返回的是单个行的对象：baseQueryObject
 * 2.向外提供一个公共的查询方法，返回的是多个行对象的一个集合：baseQueryList
 * 3.向外提供一个增删改的方法，返回的是数据库受到影响的行数：baseUpdate
 * @Author jieHFUT
 * @Create 2024/11/23 23:24
 * @Version 1.0
 */
public class BaseDao {

    /**
     * 公共的查询方法，返回的是单个的对象
     * @param clazz
     * @param sql
     * @param args
     * @param <T>
     */
    public <T> T baseQueryObject(Class<T> clazz, String sql, Object... args) {
        T t = null;
        // 获取连接池中的一个连接
        Connection connection = JdbcUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int rows = 0;
        try {
            // 准备语句对象
            preparedStatement = connection.prepareStatement(sql);
            // 设置语句中的参数
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            // 执行查询
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                // next 指针向下移动一次为 true，说明查询到了数据
                t = (T) resultSet.getObject(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (null != resultSet) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (null != preparedStatement) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                if (connection.getAutoCommit())
                    // 没有事务，可以直接关闭
                    JdbcUtil.releaseConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return t;
    }


    /**
     * 公共的查询方法，返回的是单个的集合
     * @param clazz
     * @param sql
     * @param args
     * @return
     * @param <T>
     */
    public <T> List<T> baseQueryList(Class<T> clazz, String sql, Object... args) {
        List<T> list = new ArrayList<T>();
        // 从连接池中获取连接
        Connection connection = JdbcUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int rows = 0;
        try {
            // 准备语句对象
            preparedStatement = connection.prepareStatement(sql);
            // 给语句上的占位符设置值
            for (int i = 0; i < args.length; i++)
                preparedStatement.setObject(i + 1, args[i]);
            // 执行 sql 语句
            resultSet = preparedStatement.executeQuery();


            // TODO: metaData => 装的是当前结果集 列的信息对象（可以获取列的名称根据下角标，获取列的数量）
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // 将结果集通过反射封装成为实体类对象
            while (resultSet.next()) {
                // 反射实例化对象（每一行都有一个对象）
                // 调用类的无参构造函数实例化对象
                T t = clazz.newInstance();

                // 自动遍历列（每循环一次就会得到一个 "行" 的对象）
                for (int i = 1; i <= columnCount; i++) {
                    // 对象的属性值
                    Object value = resultSet.getObject(i);
                    // 获取指定列的名称
                    String columnName = metaData.getColumnLabel(i);

                    // 反射给对象的属性值赋值
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);// 属性可以设置，打破属性私有的限制
                    /**
                     * 参数1: 要赋值的对象
                     * 参数2: 属性的具体值
                     */
                    field.set(t, value);
                }
                // 一行数据存储再 list 中
                list.add(t);
            }

        } catch (Exception e) {
             e.printStackTrace();
        } finally {


            // 关闭资源
            if (null != resultSet) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (null != preparedStatement) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                if (connection.getAutoCommit())
                    // 没有事务，可以直接关闭
                    JdbcUtil.releaseConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return list;
        }
    }


    /**
     * 通用的增删改查方法
     * @param sql
     * @param args
     * @return
     */
    public int baseUpdate(String sql, Object... args) {
        // 获取连接池中的连接
        Connection connection = JdbcUtil.getConnection();
        PreparedStatement preparedStatement = null;
        int rows = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++)
                preparedStatement.setObject(i + 1, args[i]);

            rows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            if (null != preparedStatement) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                if (connection.getAutoCommit())
                    // 没有事务，可以直接关闭
                    JdbcUtil.releaseConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return rows;
    }









}
