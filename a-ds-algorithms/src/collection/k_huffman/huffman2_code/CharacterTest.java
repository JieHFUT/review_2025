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
        byte b = -128;
        String binaryString = Integer.toBinaryString(b);
        System.out.println(binaryString); // 10111

        // byte 有8个字节，最高位0代表正数，最高位1代表负数（）
        //  23  - 10111
        // 127  - 1111111
        // -23  - 11111111111111111111111111101001 补
        //        11111111111111111111111111101000 反
        //        10000000000000000000000000010111 原

        // -128 - 11111111111111111111111110000000 补
        //        11111111111111111111111101111111 反
        //        10000000000000000000000010000000 原

        // 所以由一个 byte 类型的数字转换为 01010010 的时候，如果只想要8位数字

    }

    @Test
    public void testIntegerPaseInt(){
        System.out.println(Integer.parseInt("01001", 2));
        // 0111 - 7
        // 1011 - 11
        // 01011100 - 92
        // 10010101 - 149
        // 00010101010101001100101011001011 - 357878475

        // 8位二进制数字转化为整数是 0-255
        // 但是 byte 的范围是 -128 - 127
        byte ch = (byte) Integer.parseInt("10010101", 2);
        System.out.println(ch); // -107
    }


    @Test
    public void test(){
        byte ch = (byte) Integer.parseInt("01110101", 2);
        System.out.println(ch); // 117
        System.out.println(Integer.toBinaryString(ch)); //  1110101
                                                        // 01110101 - 少了一位
        // 1110101 | 1_00000000 = 1_01110101


        byte ch1 = (byte) Integer.parseInt("11110101", 2);
        System.out.println(ch1); // -11
        System.out.println(Integer.toBinaryString(ch1)); // 111111111111111111111111_11110101
        // 11110101 => Integer.parseInt => 245 => (byte) 强势转换 => -11 => Integer.toBinaryString => 111111111111111111111111_11110101
        // 11110100   反                                                                             111111111111111111111111_11110100 反
        // 10001011   原                                                                             100000000000000000000000_00001011 原

        // 正数：0开头，当进行提级的时候，其补码前面补充0（byte 10 - int 10）
        //      00001010 原码 => 00000000_00000000_00000000_00001010 原码
        //      00001010 反码 => 00000000_00000000_00000000_00001010 反码
        //      00001010 补码 => 00000000_00000000_00000000_00001010 补码
        // 负数：1开头，当进行提级的时候，其补码前面补充1（byte -6 - int -6）
        //      11111010 原码 => 10000000_00000000_00000000_01111010 原码
        //      10000101 反码 => 11111111_11111111_11111111_10000101 反码
        //      10000110 补码 => 11111111_11111111_11111111_10000110 补码


        // 正数：0开头，当进行强制转换的时候，其补码直接截断（int 10 - byte 10）
        //      00000000_00000000_00000000_00001010 原码 => 00001010 原码
        //      00000000_00000000_00000000_00001010 反码 => 00001010 反码
        //      00000000_00000000_00000000_00001010 补码 => 00001010 补码
        // 正数：0开头，当进行强制转换的时候，其补码直接截断（int 130 - byte -126）
        //      00000000_00000000_00000000_10000010 原码 => 11111110 原码
        //      00000000_00000000_00000000_10000010 反码 => 10000001 反码
        //      00000000_00000000_00000000_10000010 补码 => 10000010 补码
        // 负数：1开头，当进行强制转换的时候，其补码前面补充1（int -122 - byte -122）
        //      10000000_00000000_00000000_01111010 原码 => 01111010
        //      11111111_11111111_11111111_10000101 反码 => 10000101
        //      11111111_11111111_11111111_10000110 补码 => 10000110



    }

    @Test
    public void test1(){
        int y = -122;
        byte num = (byte) y;
        System.out.println(num);


    }
}
