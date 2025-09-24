package jiehfut.schedule.service;

import jiehfut.schedule.pojo.SysSchedule;

import java.util.List;

/**
 * ClassName: SysScheduleService
 * Package: jie.hfut.schedule.service
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/11/24 12:46
 * @Version 1.0
 */
public interface SysScheduleService {
    /**
     * 在用户登陆成功之后，跳转到首页，在挂载完成后查询该用户的所有日程信息
     * @param uid 参数为用户 uid
     * @return 该用户的所有日程信息 List
     */
    List<SysSchedule> findItemListByUid(int uid);

    /**
     * 用户在日程页面点击添加按钮后的操作
     * @param uid 参数为用户 UID
     * 向该用户中添加一条空的日程记录
     */
    void addDefault(int uid);

    /**
     * 前端用户在点击修改按钮后，提交日程对象到后端，在数据库中更新该日程信息
     * @param sysSchedule
     */
    void updateSchedule(SysSchedule sysSchedule);

    /**
     * 前端用户在点击删除按钮以后，将要删除的日程对象发送给后端，后端从数据库中删除该日程记录
     * @param sysSchedule
     */
    Integer removeSchedule(SysSchedule sysSchedule);

}
