package jiehfut.schedule.dao;

import jiehfut.schedule.pojo.SysSchedule;

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
