package jiehfut.schedule.dao.impl;

import jiehfut.schedule.dao.BaseDao;
import jiehfut.schedule.dao.SysScheduleDao;
import jiehfut.schedule.pojo.SysSchedule;
import jiehfut.schedule.util.JdbcUtil;

import java.util.List;

/**
 * ClassName: SysScheduleDaoImpl
 * Package: jie.hfut.schedule.dao.impl
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/11/23 22:37
 * @Version 1.0
 */
public class SysScheduleDaoImpl extends BaseDao implements SysScheduleDao {


    @Override
    public int addSysSchedule(SysSchedule sysSchedule) {
        String sql = "insert into sys_schedule values(DEFAULT, ?, ?, ?)";
        return baseUpdate(sql, sysSchedule.getUid(), sysSchedule.getTitle(), sysSchedule.getCompleted());
    }

    @Override
    public List<SysSchedule> getSysSchedule() {
        String sql = "select sid, uid, title, completed from sys_schedule";
        return baseQueryList(SysSchedule.class, sql);
    }

    @Override
    public List<SysSchedule> findItemListByUid(int uid) {
        String sql = "select sid, uid, title, completed from sys_schedule where uid = ?";
        return baseQueryList(SysSchedule.class, sql, uid);
    }

    @Override
    public void addDefault(int uid) {
        String sql = "insert into sys_schedule values (default, ?, '请输入日程', 0)";
        baseUpdate(sql, uid);
    }

    @Override
    public void updateSchedule(SysSchedule sysSchedule) {
        String sql = "update sys_schedule set title = ?, completed = ? where sid = ?";
        baseUpdate(sql, sysSchedule.getTitle(), sysSchedule.getCompleted(), sysSchedule.getSid());
    }

    @Override
    public Integer removeSchedule(SysSchedule sysSchedule) {
        String sql = "delete from sys_schedule where sid = ?";
        return baseUpdate(sql, sysSchedule.getSid());
    }


}
