package test;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: Question120
 * Package: test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/9/18 17:57
 * @Version 1.0
 */
public class Question120 {

    public static void main(String[] args) {
        List<Integer> rest1 = new ArrayList<>();
        List<Integer> rest2 = new ArrayList<>();
        List<Integer> rest3 = new ArrayList<>();
        List<Integer> rest4 = new ArrayList<>();
        List<List<Integer>> rest5 = new ArrayList<>();

        rest1.add(2);
        rest2.add(3);
        rest2.add(4);
        rest3.add(6);
        rest3.add(5);
        rest3.add(7);
        rest4.add(4);
        rest4.add(1);
        rest4.add(8);
        rest4.add(3);
        rest5.add(rest1);
        rest5.add(rest2);
        rest5.add(rest3);
        rest5.add(rest4);

        System.out.println(minimumTotal(rest5));

    }


    public static int minimumTotal(List<List<Integer>> triangle) {
        //
        int first = triangle.get(0).get(0);

        if (triangle.size() == 1) return first;

        triangle.remove(0);
        backtrack(first, 0, triangle);

        return minsum;
    }

    static int minsum = Integer.MAX_VALUE;

    public static void backtrack (int prevsum, int preindex, List<List<Integer>> rest) {
        // 判断某一次遍历是否到底
        if (rest.size() == 0) {
            if (prevsum < minsum) {
                minsum = prevsum;
            }
            return;
        }


        // 去遍历两个
        List<Integer> toOrder = rest.get(0);
        int prev = 0;
        int next = 0;
        for (int i = 0; i < toOrder.size(); i++) {
            if (i == preindex) prev = toOrder.get(i);
            if (i == preindex + 1) next =toOrder.get(i);
        }

        rest.remove(0);

        backtrack(prevsum + prev, preindex, rest);
        backtrack(prevsum + next, preindex + 1, rest);
    }
}
