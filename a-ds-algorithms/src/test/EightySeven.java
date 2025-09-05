package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * ClassName: EightySeven
 * Package: test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/8/29 18:15
 * @Version 1.0
 */
public class EightySeven {

    public static void main(String[] args) {

        System.out.println(isScramble("yueyshdjeiohfjqweiohdcoiashd", "yueyshdjeiohfjqweiohdcoiashd"));
    }


    public static boolean isScramble(String s1, String s2) {
        // 思路：自己对原字符串 s1 进行扰动，如果某一刻等于 s2, 则是，否则不是 =>
        List<String> ans = recursion(s1);
        for (String str : ans) {
            if (str.equals(s2)) {
                return true;
            }
        }
        return false;
    }



    // 返回所有可能的扰乱结果
    public static List<String> recursion(String str) {
        if (str.length() == 1) {
            List<String> ret = new ArrayList<>();
            ret.add(str);
            return ret;
        }

        List<String> ret = new ArrayList<>();

        // 长度大于等于 2 的时候需要进行随机分割
        for (int i = 0; i < str.length() - 1; i++) {
            // 从 i 位置开始分割 [0,i] && [i + 1, n - 1]
            String s1 = str.substring(0, i + 1);
            String s2 = str.substring(i + 1, str.length());

            List<String> list1 = recursion(s1);
            List<String> list2 = recursion(s2);

            // 对这两个集合进行整合
            for (String str1 : list1) {
                for (String str2 : list2) {
                    // 正反手加入
                    ret.add(str1 + str2);
                    ret.add(str2 + str1);
                }
            }

        }
        // 进行去重
        HashSet<String> set = new HashSet<>(ret);

        return new ArrayList<>(set);
    }
}
