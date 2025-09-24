package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: FourtyTwo
 * Package: test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/8/24 14:27
 * @Version 1.0
 */
public class FourtyTwo {

    public static void main(String[] args) {
        System.out.println(trap1(new int[]{764,0}));
    }

    public static int trap1(int[] height) {
        // 3.使用动态规划
        // 考虑下标为 i 的位置，在求其前面的最大值时：maxLeft(i) = maxLeft(i-1) || height(i-1)
        List<int[]> dp = new ArrayList<>();
        dp.add(new int[]{0,0});
        dp.add(new int[]{0,0});
        int[] ints = dp.get(1);
        for(int num : ints) {
            System.out.println(num);
        }
        return 0;
    }


    public static int trap(int[] height) {
        // 思路一：按行求解
        int len = height.length;
        if (len <= 2) return 0;
        int sumArea = 0;
        int high = 1;
        while (true) {
            // 先找到第一个该高度存在的柱子
            int i = 0;
            for (i = 0; i < len; i++) {
                if (height[i] - high >= 0) {
                    break;
                }
            }
            // 先找到最后一个该高度存在的柱子
            int j = len - 1;
            for (j = len - 1; j > -1; j--) {
                if (height[j] - high >= 0) {
                    break;
                }
            }
            // 如果两个柱子在一起或者已经到了边界，就不用再继续下去了
            if (i >= len - 2 || j <= 1 || j <= i + 1) {
                break;
            } else {
                // 添加两个柱子之间的空白数据
                for (int k = i + 1; k < j; k++) {
                    if (height[k] - high < 0) {
                        sumArea++;
                    }
                }
                // 计算完成这一行面积后，增加高度
                high++;
            }
        }
        return sumArea;
    }
}
