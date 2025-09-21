package test;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: Question122
 * Package: test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/9/21 21:45
 * @Version 1.0
 */
public class Question122 {
    public static void main(String[] args) {

        System.out.println(maxProfit(new int[]{34,82,16,74,55,46,44,25,96,
                80,51,62,73,26,76,30,20,30,55,6,3,93,74,80,8,13,3,82,1,35,
                68,22,81,63,77,41,51,84,36,46,86,71,5,65,65,91,97,57,92,96,
                57,97,74,33,19,42,44,22,12,26}));
    }


    public static int maxProfit(int[] prices) {
        // 采用回溯
        List<Integer> rest = new ArrayList<>();
        for(int num : prices) rest.add(num);
        backtrack(0, rest);

        return ret;
    }


    static int ret = 0;

    /**
     * @param prevSum 前面的利益合计
     * @param rest 剩下的数字
     *
     */
    public static void backtrack(int prevSum, List<Integer> rest) {

        if (isFailed(rest)) {
            // 还有机会

        } else {
            // 没有机会
            if (prevSum > ret) ret = prevSum;
            return;
        }

        // 剩下的数字
        int minprev = Integer.MAX_VALUE;
        List<Integer> used = new ArrayList<>();

        for (int i = 1; i < rest.size(); i++) {
            used.add(rest.get(i));
        }



        for (int i = 1; i < rest.size(); i++) {

            if (rest.get(i - 1) < minprev) minprev = rest.get(i - 1);
            // 以 i 结尾
            int numi = rest.get(i);
            used.remove(0);

            if (minprev >= numi) {
                //
                backtrack(prevSum, used);
            } else {
                //
                backtrack(prevSum + numi - minprev, used);
            }
        }
    }

    public static boolean isFailed(List<Integer> rest) {
        // 如果是升序的就返回 false
        if (rest.size() <= 1) return false;
        //
        int num = rest.get(0);
        for (int i = 1; i < rest.size(); i++) {
            if (rest.get(i) > num) {
                return true;
            } else {
                num = rest.get(i);
            }
        }
        return false;
    }
}
