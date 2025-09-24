package leetcode;

import java.util.*;

/**
 * ClassName: FiftySix
 * Package: test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/8/25 17:53
 * @Version 1.0
 */
public class FiftySix {

    public static void main(String[] args) {

//        int[][] merge = merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}});
//        for (int[] ints : merge) {
//            System.out.print(Arrays.toString(ints) + "\t");
//        }
//
//        List<int[]> list = new ArrayList<>();
//        list.add(new int[]{1,2});
//        int[] remove = list.remove(0);
//        System.out.println(Arrays.toString(remove));
//        list.add(0, new int[]{1,2,3});
//
//        for(int[] ints : list){
//            System.out.print(Arrays.toString(ints) + "\t");
//        }




    }


    public int lengthOfLastWord(String s) {
        String str = s.trim();
        String[] strs = str.split(" ");
        return strs[strs.length - 1].length();
    }


    public static int[][] merge(int[][] intervals) {
        // 上面错在 [1,4] && [5,6] != [1,6]
        // 思路：使用递归
        List<int[]> next = new ArrayList<>();
        for (int[] ints : intervals) {
            next.add(ints);
        }

        List<int[]> prev = new ArrayList<>();
        prev.add(next.remove(0));

        setFirst(prev, next);
        return prev.toArray(new int[next.size()][]);
    }

    // 每次从 next 中取出来一个加入到 prev 中
    public static void setFirst(List<int[]> prev, List<int[]> next) {
        if (next.size() == 0) return;

        int[] first = next.remove(0);
        int leftf = first[0];
        int rightf = first[1];

        // 将 ints 数组并入到 prev 集合中
        // 1.去集合中直接去和最后一个位置相比
        int[] last = prev.remove(prev.size() - 1);
        int leftl = last[0];
        int rightl = last[1];

        if (leftf > rightl) {
            prev.add(last);
            prev.add(first);
            setFirst(prev, next);
        } else {
            prev.add(new int[]{leftl, rightf});
            setFirst(prev, next);
        }
    }



    public static int[][] merge1(int[][] intervals) {
        // 将所有的数字否放入一个 set 中，然后从 min 开始问 set 中有没有
        int minNum = Integer.MAX_VALUE;
        int maxNum = Integer.MIN_VALUE;
        HashSet<Integer> set = new HashSet<>();
        for(int[] nums : intervals) {
            // 对于每一个区间，记录一下是否是最大值，并且将其内所有数字放到 set 中去
            int left = nums[0];
            int right = nums[1];

            if (left < minNum) minNum = left;
            if (right > maxNum) maxNum = right;
            for (int i = left; i <= right; i++) {
                set.add(i);
            }
        }

        List<int[]> lists = new ArrayList<>();
        // 开始从 min -> max 问 set
        int start = minNum;
        int end = minNum - 1;
        for (int i = minNum; i <= maxNum + 1; i++) {
            if (set.contains(i)) {
                // 包含这个数字
                end = i;
            } else {
                // 不包含这个数字
                if (end >= start) {
                    lists.add(new int[]{start, end});
                }

                start = i + 1;
                end = start - 1;
            }
        }
        return lists.toArray(new int[lists.size()][]);
    }
}
