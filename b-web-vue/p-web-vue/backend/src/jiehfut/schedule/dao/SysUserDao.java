package jiehfut.schedule.dao;

import jiehfut.schedule.pojo.SysUser;

/**
 * ClassName: SysUserDao
 * Package: jie.hfut.schedule.dao
 * Description:
 * DAO（Data access Object：数据访问对象）
 * 该类用于定义针对表格的 CRUD 的方法
 * 该层一般需要实现接口（规范不同的人实现增删改查的方法，让上层调用者只需要关注接口即可）和实现类
 * 在定义接口的时候需要对接口添加一些文档
 *
 * @Author jieHFUT
 * @Create 2024/11/23 22:30
 * @Version 1.0
 */

public interface SysUserDao {

    /**
     * 这是向数据库中增加一条用户记录的方法
     * @param sysUser 要增加的记录的 username && userPwd 字段，以 SysUser 对象的形式接收（null, username, userPwd）
     * @return 返回受到影响的行数
     */
    int addSysUser(SysUser sysUser);

    /**
     * 这是根据用户名获得完成用户信息的方法
     * @param username 要查询的用户名
     * @return 如果找到了就返回查询到的用户对象，找不到就返回 null
     */
    SysUser findByUsername(String username);
}
