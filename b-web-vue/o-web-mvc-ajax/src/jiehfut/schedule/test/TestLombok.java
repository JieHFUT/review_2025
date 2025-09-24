package jiehfut.schedule.test;

import jiehfut.schedule.pojo.SysUser;
import org.junit.Test;

/**
 * ClassName: TestLombok
 * Package: jie.hfut.schedule.test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/11/23 22:19
 * @Version 1.0
 */
public class TestLombok {
    @Test
    public void test(){
        // 1.调用无参构造器
        SysUser sysUser = new SysUser();
        // 2.调用 getter && setter
        System.out.println(sysUser.getUsername());
        sysUser.setUsername("xiaomin");
    }
}
