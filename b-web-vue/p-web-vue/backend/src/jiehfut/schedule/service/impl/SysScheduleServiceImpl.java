package jiehfut.schedule.service.impl;

import jiehfut.schedule.dao.SysScheduleDao;
import jiehfut.schedule.dao.impl.SysScheduleDaoImpl;
import jiehfut.schedule.pojo.SysSchedule;
import jiehfut.schedule.service.SysScheduleService;

import java.util.List;

/**
 * ClassName: SysScheduleServiceImpl
 * Package: jie.hfut.schedule.service.impl
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/11/24 12:46
 * @Version 1.0
 */
public class SysScheduleServiceImpl implements SysScheduleService {

    private SysScheduleDao sysScheduleDao = new SysScheduleDaoImpl();

    @Override
    public List<SysSchedule> findItemListByUid(int uid) {
        return sysScheduleDao.findItemListByUid(uid);
    }

    @Override
    public void addDefault(int uid) {
        sysScheduleDao.addDefault(uid);
    }

    @Override
    public void updateSchedule(SysSchedule sysSchedule) {
        sysScheduleDao.updateSchedule(sysSchedule);
    }

    @Override
    public Integer removeSchedule(SysSchedule sysSchedule) {
        return sysScheduleDao.removeSchedule(sysSchedule);
    }
}
