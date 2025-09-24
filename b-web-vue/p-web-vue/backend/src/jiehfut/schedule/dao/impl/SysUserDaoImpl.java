package jiehfut.schedule.dao.impl;

import jiehfut.schedule.dao.BaseDao;
import jiehfut.schedule.dao.SysUserDao;
import jiehfut.schedule.pojo.SysUser;

import java.util.List;

/**
 * ClassName: SysUserDaoImpl
 * Package: jie.hfut.schedule.dao.impl
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/11/23 22:37
 * @Version 1.0
 */
public class SysUserDaoImpl extends BaseDao implements SysUserDao {

    @Override
    public int addSysUser(SysUser sysUser) {
        String sql = "insert into sys_user values(DEFAULT,?,?)";
        return baseUpdate(sql, sysUser.getUsername(), sysUser.getUserPwd());
    }

    @Override
    public SysUser findByUsername(String username) {
        String sql = "select uid, username, user_pwd userPwd from sys_user where username = ?";
        List<SysUser> sysUserList = baseQueryList(SysUser.class, sql, username);
        return sysUserList != null && sysUserList.size() > 0 ? sysUserList.get(0) : null;
    }






}
