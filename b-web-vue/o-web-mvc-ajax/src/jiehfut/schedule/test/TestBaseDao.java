package jiehfut.schedule.test;

import jiehfut.schedule.dao.BaseDao;
import jiehfut.schedule.pojo.SysUser;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

/**
 * ClassName: TestBaseDao
 * Package: jie.hfut.schedule.test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/11/23 23:04
 * @Version 1.0
 */
public class TestBaseDao {

    private static BaseDao baseDao;

    @BeforeClass
    public static void initBaseDao() {
        baseDao = new BaseDao();
    }



    /**
     * 测试返回多个对象的查询结果集
     */
    @Test
    public void testBaseQueryList(){
        // 测试 sys_user 中的所有数据
        String sql = "select uid, username, user_pwd userPwd from sys_user";
        List<SysUser> sysUserList = baseDao.baseQueryList(SysUser.class, sql);
        sysUserList.forEach(System.out::println);
    }


    /**
     * 测试返回单个对象的查询结果集
     */
    @Test
    public void testBaseQueryObject(){
        // 查询表 sys_user 用户数量
        String sql = "select count(1) from sys_user";
        Long count = baseDao.baseQueryObject(Long.class, sql);
        System.out.println("用户表中一共有用户 " + count + " 个");
    }
    
    @Test
    public void testBaseUpdate(){
        // 向 sys_schedule 表中插入一条数据  （自增，用户ID，日程内容，日程是否完成）
        String sql = "insert into sys_schedule values(DEFAULT, ?, ?, ?)";
        int rows = baseDao.baseUpdate(sql, 1, "study web", 0);
        System.out.println("rows = " + rows);
    }
}
