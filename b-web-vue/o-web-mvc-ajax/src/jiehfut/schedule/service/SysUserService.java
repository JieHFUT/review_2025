package jiehfut.schedule.service;

import jiehfut.schedule.pojo.SysUser;

/**
 * ClassName: SysUserService
 * Package: jie.hfut.schedule.service
 * Description:
 * 该接口定义了以 sys_user 为核心的业务处理功能的接口
 * @Author jieHFUT
 * @Create 2024/11/24 12:12
 * @Version 1.0
 */
public interface SysUserService {


    /**
     * 这是注册用户的方法
     * @param sysUser：要注册的用户名和明文密码，以 SysUser 对象的形式接收（null, username, userPwd）
     * @return 注册成功返回1，注册失败返回0
     */
    int regist(SysUser sysUser);

    /**
     * 这是根据用户名获得完成用户信息的方法
     * @param username 要查询的用户名
     * @return 如果找到了就返回查询到的用户对象，找不到就返回 null
     */
    SysUser findByUsername(String username);
}
