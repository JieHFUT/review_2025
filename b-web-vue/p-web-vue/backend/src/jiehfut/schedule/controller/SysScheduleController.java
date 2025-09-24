package jiehfut.schedule.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jiehfut.schedule.common.Result;
import jiehfut.schedule.pojo.SysSchedule;
import jiehfut.schedule.service.SysScheduleService;
import jiehfut.schedule.service.impl.SysScheduleServiceImpl;
import jiehfut.schedule.util.WebUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: SysScheduleNewController
 * Package: jie.hfut.schedule.controller
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/11/24 13:37
 * @Version 1.0
 */

@WebServlet(value = "/schedule/*",  name = "scheduleServlet")
public class SysScheduleController extends BaseController {

    // 服务层对象
    private SysScheduleService sysScheduleService = new SysScheduleServiceImpl();

    // 视为已经实现了 service 方法
    // 对上面的方法进行封装
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("add");
    }
    protected void find(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("find");
    }
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("update");
    }
    protected void remove(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("remove");
    }

    /**
     * 登陆完成进入主页面，在挂载完成后查询该用户的所有日程信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findAllSchedule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收请求中的 uid 参数，查询用户的所有日程信息
        int uid = Integer.parseInt(req.getParameter("uid"));
        List<SysSchedule> items = sysScheduleService.findItemListByUid(uid);
        // 将所有的日程信息放入一个 Result 对象
        Map data = new HashMap();
        data.put("items", items);
        Result result = Result.ok(data);
        WebUtil.writeJson(resp, result);
    }

    protected void addDefaultSchedule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收请求中的 uid 参数
        int uid = Integer.parseInt(req.getParameter("uid"));
        // 调用服务层的方法，向数据库中添加一条空的记录
        sysScheduleService.addDefault(uid);
        // 获得增加结果，响应结果
        WebUtil.writeJson(resp, Result.ok(null));
    }

    protected void updateSchedule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 前端点击修改按钮后发送该 日程对象发送到后端
        // 接收日程对象，更新数据库中对应的日程，给前端成功响应
        SysSchedule sysSchedule = WebUtil.readJson(req, SysSchedule.class);
        sysScheduleService.updateSchedule(sysSchedule);

        WebUtil.writeJson(resp, Result.ok(null));
    }



    protected void removeSchedule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 前端点击修改按钮后发送该 日程对象发送到后端
        // 接收日程对象，更新数据库中对应的日程，给前端成功响应
        SysSchedule sysSchedule = WebUtil.readJson(req, SysSchedule.class);
        Integer data = sysScheduleService.removeSchedule(sysSchedule);

        WebUtil.writeJson(resp, Result.ok(data));
    }
}
