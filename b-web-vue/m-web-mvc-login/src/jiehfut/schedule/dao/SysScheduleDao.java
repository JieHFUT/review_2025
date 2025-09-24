package jiehfut.hfut.schedule.dao;

import jiehfut.hfut.schedule.pojo.SysSchedule;

import java.util.List;

/**
 * ClassName: SysScheduleDao
 * Package: jie.hfut.schedule.dao
 * Description:
 * DAO（Data access Object：数据访问对象）
 * 该类用于定义针对表格的 CRUD 的方法
 * 该层一般需要实现接口（规范不同的人实现增删改查的方法，让上层调用者只需要关注接口即可）和实现类
 * 在定义接口的时候需要对接口添加一些文档
 *
 * @Author jieHFUT
 * @Create 2024/11/23 22:31
 * @Version 1.0
 */
public interface SysScheduleDao {

    /**
     * 用于向数据中添加一条日程记录
     * @param sysSchedule 日程记录以 SysSchedule 实体类对象形式如参
     * @return 返回影响数据库记录的行数，为 0说明增加失败，行数大于零说明添加失败
     */
    int addSysSchedule(SysSchedule sysSchedule);

    /**
     * 查询所有用户所有的日程
     * @return 将所有的日程放入一个：List<SysSchedule> 集合中返回
     */
    List<SysSchedule> getSysSchedule();



}
