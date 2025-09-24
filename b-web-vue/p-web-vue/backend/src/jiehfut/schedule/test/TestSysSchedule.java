package jiehfut.schedule.test;

import jiehfut.schedule.dao.SysScheduleDao;
import jiehfut.schedule.dao.impl.SysScheduleDaoImpl;
import jiehfut.schedule.pojo.SysSchedule;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.List;

/**
 * ClassName: TestSysSchedule
 * Package: jie.hfut.schedule.test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/11/24 12:03
 * @Version 1.0
 */
public class TestSysSchedule {

    private static SysScheduleDao sysScheduleDao;

    @BeforeClass
    public static void initSysSchedule() {
        sysScheduleDao = new SysScheduleDaoImpl();
    }


    @Test
    public void testAddSchedule(){
        int rows = sysScheduleDao.addSysSchedule(new SysSchedule(null, 2, "study mysql", 0));
        System.out.println("rows = " + rows);
    }
    
    @Test
    public void testFindAllSchedule(){
        List<SysSchedule> sysScheduleList = sysScheduleDao.getSysSchedule();
        sysScheduleList.forEach(System.out::println);
    }

    
}
