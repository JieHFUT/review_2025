package jiehfut.schedule.util;


import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ClassName: JdbcUtil
 * Package: util
 * Description:
 * 该工具类用于使用德鲁伊库连接池封装连接数据库的方法
 * 1.向外提供获取获取连接池的方法：getDataSource()
 * 2.向外提供从连接池中获取一个连接的方法：getConnection()
 * 3.向外提供一个释放连接到连接池的方法：releaseConnection()
 *
 * @Author jieHFUT
 * @Create 2024/11/20 22:34
 * @Version 1.0
 */
public class JdbcUtil {

    // 利用本地线程变量，存储连接信息！确保一个线程的多个方法使用的是同一个 Connection
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    private static DataSource dataSource;

    // 初始化连接池
    static {
        // 读取配置的设置数据库的文件
        Properties properties = new Properties();
        // 获得配置文件中的配置信息流
        InputStream resourceAsStream =
                JdbcUtil.class.getClassLoader().getResourceAsStream("druid.properties");
        // 将配置文件中得到的输入流放到 properties 对象中去
        try {
            properties.load(resourceAsStream);
            // 使用德鲁伊获得配置信息，得到数据库连接源
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    // 向外提供连接池子的方法
    public static DataSource getDataSource() {
        return dataSource;
    }


    /**
     * 对外提供一个获取连接池的方法
     * 注意下面方法中调用 dataSource.getConnection()并不是调用自己
     * 两个 getConnection() 是不同的方法
     * @return
     */
    public static Connection getConnection() {
        // 先查看线程本地变量中是否存在连接
        Connection connection = threadLocal.get();
        if (connection == null) {
            // 线程本地变量没有就通过连接池获取
            try {
                connection = dataSource.getConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            threadLocal.set(connection);
        }
        return connection;
    }


    /**
     * 对外提供一个归还连接的方法
     * @param
     */
    public static void releaseConnection()  {
        Connection connection = threadLocal.get();
        if (connection != null) {
            // 清空线程本地变量数据
            threadLocal.remove();
            // 事务状态回归
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }














}
