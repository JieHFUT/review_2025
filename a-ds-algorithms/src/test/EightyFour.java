package test;

/**
 * ClassName: EightyFour
 * Package: test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/8/29 14:10
 * @Version 1.0
 */
public class EightyFour {

    public static void main(String[] args) {
        System.out.println(largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
    }


    public static int largestRectangleArea1(int[] heights) {
        // 思路一：按高度求
        int height = 1;
        int maxArea = 0;
        int len = heights.length;

        while(true) {
            // 记录这个高度时，连续的格子有多少
            int count = 0; // 一次连续
            int maxcount = 0; // 该高度连续
            int thisOk = 0;

            int index = 0;
            while (index < len) {
                if (heights[index] >= height) {
                    count++;
                    index++;
                } else {
                    if (count > maxcount) maxcount = count;
                    count = 0;
                    index++;
                }
            }
            // 考虑如果全部都是大于该高度的，或者最后一批没有进去 else
            maxcount = count > maxcount ? count : maxcount;

            // 比较该高度的最长连续格子面积
            if (maxcount * height > maxArea) maxArea = maxcount * height;
            if (maxcount < 1) break;

            // 进行下一个高度
            height++;
        }

        return maxArea;
    }


    public static int largestRectangleArea(int[] heights) {
        // 思路二：使用动态规划
        // 定义 dp[i][j] 是从下标 i 到下标 j 内的 (j - i + 1) 个元素中最矮的元素
        // 则其状态转移方程是：dp[i][j] = Math.min(dp[i][j-1], heights[j]) => 就是左边

        int[][] dp = new int[heights.length][heights.length];

        int maxArea = 0;

        // 边界条件（对角线）
        for (int i = 0; i < heights.length; i++) {
            dp[i][i] = heights[i];
            if (dp[i][i] > maxArea) maxArea = dp[i][i];
        }


        // 开始进行动态规划
        for (int i = 0; i < heights.length; i++) {
            for (int j = i + 1; j < heights.length; j++) {
                dp[i][j] = Math.min(dp[i][j-1], heights[j]);
                if (((j - i + 1) * dp[i][j]) > maxArea) maxArea = (j - i + 1) * dp[i][j];
            }
        }
        return maxArea;
    }
}
