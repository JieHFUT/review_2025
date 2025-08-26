package test;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: FourtyFive
 * Package: test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/8/24 18:20
 * @Version 1.0
 */
public class FourtyFive {
    public static void main(String[] args) {
//        System.out.println(jump(new int[]{2, 3, 1, 1, 4}));

    }













    public static int jump(int[] nums) {
        // 1.使用回溯算法
        findStep(nums, 0, 0);
        return min_step;
    }

    static int min_step = Integer.MAX_VALUE;
    /**
     * @param nums 输入的原始数组
     * @param step 到达 index 下标已经走的步数
     * @param index 现在处于下标为 index 的位置
     */
    public static void findStep(int[] nums, int step, int index) {
        if (index == nums.length - 1) {
            // 找到了一个结果
            if (step < min_step) min_step = step;
            return;
        }

        // index 没有到达数组的最后一位，开始对这位上的数字进行遍历
        int path = nums[index];
        for (int i = 1; i <= path; i++) {
            if (index + i > nums.length - 1) {
                // 跳过头了，不考虑
            } else {
                //
                findStep(nums, step + 1, index + i);
            }
        }
    }





    public int jump2(int[] nums) {
        // 思路一：采用回溯法
        minStep(nums, 0, nums.length - 1, 0);
        return minStep;
    }



    int minStep = Integer.MAX_VALUE;
    /**
     * @param nums 传递的原始数组
     * @param step 目前到这个下标已经跳跃的次数
     * @param restLength 还剩下的距离
     * @param index 目前处于的下标
     */
    public void minStep(int[] nums, int step, int restLength, int index) {
        // 如果还剩下的距离是 0，就说明刚好到达终点，记录一下
        if (restLength == 0) {
            if (step < minStep) minStep = step;
            return;
        }
        // 如果剩下的距离小于 0，就不用再跳了
        if (restLength < 0) return;

        // 剩下的距离大于 0，遍历目前位置大小的步数（从 1 开始）
        int key = nums[index];

        for (int i = 1; i <= key; i++) {
            if (i > restLength) {
                break;
            } else {
                if (index + i < nums.length) {
                    minStep(nums, step + 1, restLength - i, index + i);
                }
            }
        }
    }



















    public int jump1(int[] nums) {
        // 思路二：采用递归
        return minStep(nums, 0);
    }

    // 返回这个下标到终点的最短距离
    public int minStep(int[] nums, int index) {
        if (index == nums.length - 1) {
            // 找到一个结果
            return 0;
        }

        int key = nums[index];
        int thisMin = Integer.MAX_VALUE - 8;

        for (int i = 1; i <= key; i++) {
            // 这个位置遍历一遍，返回 index + i 位置到终点的最小步数
            if (index + i < nums.length) {
                int nextStep = minStep(nums, index + i);
                if (nextStep < thisMin) {
                    thisMin = nextStep;
                }
            }
        }
        // 也就是说是从这个位置直接跳到 i 位置的步数最小
        return 1 + thisMin;
    }

}
