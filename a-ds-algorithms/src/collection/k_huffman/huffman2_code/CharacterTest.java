package collection.k_huffman.huffman2_code;

import org.junit.Test;

import javax.print.attribute.standard.JobKOctets;
import java.io.UnsupportedEncodingException;

/**
 * ClassName: CharacterTest
 * Package: collection.k_huffman.huffman2_code
 * Description:
 * 编码测试
 * @Author jieHFUT
 * @Create 2025/7/30 14:07
 * @Version 1.0
 */
public class CharacterTest {
    public static void main(String[] args) throws UnsupportedEncodingException {

        byte[] utf8s = "我".getBytes("utf8");
        for (byte b : utf8s) {
            System.out.println(b); // -26 -120 -111
        }
        /**
         * "我"：-26 -120 -111
         * "a"：97
         */
        

        byte[] gbks = "我".getBytes("gbk");
        for (byte b : gbks) {
            System.out.println(b); // -50 -46
        }
        /**
         * "我"：-50 -46
         * "a"：97
         */

        char ch = '我';
        byte[] utf8s1 = String.valueOf(ch).getBytes("utf8");
        for (byte b : utf8s1) {
            char a = (char) b;
            System.out.println(a);
        }
        // ￦
        // ﾈ
        // ﾑ
    }
    
    
    @Test
    public void testByte(){
        byte b = 23;
        String binaryString = Integer.toBinaryString(b);
        System.out.println(binaryString);


    }
}
