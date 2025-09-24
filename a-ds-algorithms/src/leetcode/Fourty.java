package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * ClassName: Fourty
 * Package: test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/8/24 13:19
 * @Version 1.0
 */
public class Fourty {


    public static void main(String[] args) {
        List<List<Integer>> lists = combinationSum2(new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,11,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}, 30);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }



    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 每个数字只能使用一次
        Arrays.sort(candidates);
        combination(candidates, candidates.length - 1, target);

        // 还需要对 ret401 进行去重
        HashSet<List<Integer>> set = new HashSet<>(ret401);
        return new ArrayList<>(set);
    }

    // 用来记录一次有效的序列
    static List<Integer> listss = new ArrayList<>();
    static List<List<Integer>> ret401 = new ArrayList<>();
    /**
     *
     * @param candidates 原始数组
     * @param target 从大往小，已经去掉了多少大数，还剩下的 target
     * @param index 目前该讨论的数字，后面比他大的数字都遍历过了
     */
    public static void combination(int[] candidates, int index, int target) {
        if (target == 0) {
            // 找到一组有效的序列
            ret401.add(new ArrayList<>(listss));
            return;
        }

        if (index == -1) return;

        if (candidates[index] > target) {
            // 不能有这个数字

            combination(candidates, index - 1, target);
        } else {
            // 可能有这个数字 => 两种情况
            listss.add(candidates[index]);
            combination(candidates, index - 1, target - candidates[index]);
            listss.remove(listss.size() - 1);

            combination(candidates, index - 1, target);
        }
    }
}
