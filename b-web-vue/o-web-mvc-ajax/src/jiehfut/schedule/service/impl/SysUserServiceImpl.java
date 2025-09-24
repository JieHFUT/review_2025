package jiehfut.schedule.service.impl;

import jiehfut.schedule.dao.SysUserDao;
import jiehfut.schedule.dao.impl.SysUserDaoImpl;
import jiehfut.schedule.pojo.SysUser;
import jiehfut.schedule.service.SysUserService;
import jiehfut.schedule.util.MD5Util;

/**
 * ClassName: SysUserServiceImpl
 * Package: jie.hfut.schedule.service.impl
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/11/24 12:45
 * @Version 1.0
 */
public class SysUserServiceImpl implements SysUserService {

    private SysUserDao userDao = new SysUserDaoImpl();

    @Override
    public int regist(SysUser sysUser) {
        // 将用户的明文密码转换成为密文密码
        sysUser.setUserPwd(MD5Util.encrypt(sysUser.getUserPwd()));
        // 利用 DAO 层的方法将 sysUser 对象信息存入数据库
        return userDao.addSysUser(sysUser);
    }

    @Override
    public SysUser findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
