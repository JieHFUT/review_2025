package test;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: SeventySix
 * Package: test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/8/28 15:47
 * @Version 1.0
 */
public class SeventySix {

    public static void main(String[] args) {

        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }


    public static String minWindow(String s, String t) {
        // s = "ssdjrwhghhhjeqiryo"
        // t = ="hhgwjj"
        HashMap<Character, Integer> mapt = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            // 如果没有这个字符，加入设置 value 为一
            // 如果已有这个字符，设置 value++
            mapt.put(t.charAt(i), mapt.getOrDefault(t.charAt(i), 0) + 1);
        }

        // 思路：考虑使用滑动窗口
        int rk = -1; // 右指针
        int minLen = Integer.MAX_VALUE;
        int start = -1;
        int end = -1;
        HashMap<Character, Integer> maps = new HashMap<>();
        // 从左指针开始进行遍历
        for (int i = 0; i < s.length(); i++) {
            // 记录从这个位置是否找到符合要求的结果，如果找不到就不必再找了
            boolean isFound = false;

            if (i != 0) {
                // 从 i-1 已经开始遍历过，将 i-1 位置的值删除掉，判断从 i 开始遍历的结果
                if (maps.get(s.charAt(i - 1)) > 1) {
                    maps.put(s.charAt(i - 1), maps.get(s.charAt(i - 1)) - 1);
                } else {
                    maps.remove(s.charAt(i - 1));
                }
            }

            // 判断 maps 中是否包含 mapt 的全部数据，如果包含记录（start end = i rk）
            // 如果遍历到终点也不包含就不必再继续了
            // 针对从这个 i 下标开始的位置进行遍历
            // 看这个下标开始的序列包不包含符合要求的，如果符合记录其最短长度
            while (rk + 1 < s.length() && !isContain(mapt, maps)) {
                // 添加 rk + 1 元素
                maps.put(s.charAt(rk + 1), maps.getOrDefault(s.charAt(rk + 1), 0) + 1);
                rk++;
            }

            // maps 包含 mapt 或者 maps 中已经添加 s 的所有元素
            if (isContain(mapt, maps)) {
                if (rk - i + 1 < minLen) {
                    minLen = rk - i + 1;
                    start = i;
                    end = rk;
                }
                // 判断是否需要进行下一步
//                if (rk + 1 >= s.length())
//                    break;
            } else {
                // 不包含，并且到头了
                break;
            }
        }

        if (minLen == Integer.MAX_VALUE) {
            return "";
        } else {
            return s.substring(start, end + 1);
        }

    }

    /**
     * 判断 maps 中是否包含 mapt 的所有数据
     * 遍历 mapt 判断 maps 中是否全部包含
     * @param mapt
     * @param maps
     * @return
     */
    public static boolean isContain(HashMap<Character, Integer> mapt, HashMap<Character, Integer> maps) {
        // 遍历 mapt
        for (Map.Entry<Character, Integer> entry : mapt.entrySet()) {
            char ch = entry.getKey();
            int num = entry.getValue();
            // 判断 maps 中该字符的数量是否大于等于 num
            if (!maps.containsKey(ch)) {
                return false;
            } else {
                if (maps.get(ch) < num) {
                    return false;
                }
            }

        }

        return true;
    }
}
