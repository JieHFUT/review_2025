package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: EightyNine
 * Package: test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/9/9 20:20
 * @Version 1.0
 */
public class EightyNine {

    public static void main(String[] args) {
        System.out.println(grayCode(3));
    }

    public static List<Integer> grayCode(int n) {
        List<String> stringResult = getGrayCode(n);
        List<Integer> ret = new ArrayList<>();

        for (String result : stringResult) {
            ret.add(Integer.parseInt(result, 2));
        }

        return ret;
    }



    public static List<String> getGrayCode(int n) {

        List<String> ret = new ArrayList<>();
        if (n == 1) {
            ret.add("0");
            ret.add("1");
            return ret;
        } else {
            // 获取上一层的所有数据
            List<String> prev = getGrayCode(n - 1);
            // 在前面添加字符串 "0", 然后顺序添加
            for(int i = 0; i < prev.size(); i++) {
                ret.add("0" + prev.get(i));
            }
            // 在前面添加字符串 "1"，然后逆序添加
            for(int i = prev.size() - 1; i >= 0; i--) {
                ret.add("1" + prev.get(i));
            }
        }

        return ret;
    }
}
