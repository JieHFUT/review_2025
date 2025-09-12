package test;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: NietyThree
 * Package: test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/9/11 14:20
 * @Version 1.0
 */
public class NietyThree {


    public static void main(String[] args) {
        List<String> strings = restoreIpAddresses("101023");
        strings.forEach(System.out::println);

    }



    public static List<String> restoreIpAddresses(String s) {
        // 思路：采用回溯（只包含数字）
        List<Integer> rest = new ArrayList<>();
        for(int i = 0; i < s.length(); i++) {
            rest.add(s.charAt(i) - '0');
        }

        backtrack(new ArrayList<>(), rest);
        return ret;
    }


    static List<String> ret = new ArrayList<>();

    public static void backtrack(List<String> used, List<Integer> rest) {
        if (used.size() == 4) {
            if (rest.size() == 0) {
                // 获得一个有效的组合
                ret.add(used.get(0) + "." + used.get(1) + "." + used.get(2) + "." + used.get(3));
                return;
            } else {
                return;
            }
        }

        // 向 used 里面放有效的 ip
        if (rest.size() >0 && rest.get(0) == 0) {
            // 剩余的数字第一个是 0，那么这个合法的数字只有可能是 0
            used.add("0");
            rest.remove(0);

            backtrack(used, rest);

            used.remove(used.size() - 1);
            rest.add(0, 0);
        } else {
            // 剩余的数字第一个不是 0
            int target = 0;
            for (int i = 0; i < rest.size() && i < 3; i++) {
                // 最长三位数字 => 0 - 255
                target = 10 * target + rest.get(i);

                if (target >= 0 && target < 256) {
                    // 符合条件
                    used.add(target + "");
                    int[] record = new int[i + 1];
                    for (int j = 0; j <= i; j++) {
                        record[j] = rest.get(0);
                        rest.remove(0);
                    }

                    backtrack(used, rest);

                    used.remove(used.size() - 1);
                    for(int j = i; j >= 0 ; j--) {
                        rest.add(0, record[j]);
                    }
                }
            }
        }


    }
}
