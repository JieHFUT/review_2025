package jiehfut.schedule.test;

import jiehfut.schedule.util.MD5Util;
import org.junit.Test;

/**
 * ClassName: TestMD5Util
 * Package: jie.hfut.schedule.test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/11/24 14:02
 * @Version 1.0
 */
public class TestMD5Util {

    @Test
    public void testEncrypt(){
        // 测试将明文转换为密文
        String encrypt = MD5Util.encrypt("123456");
        System.out.println(encrypt);
    }
}
