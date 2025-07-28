package collection.e_recursion;

import jdk.jfr.DataAmount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName: Queue8
 * Package: collection.e_recursion
 * Description:
 * 8皇后问题
 * 8*8 棋盘，任意两个棋子不同行，不同列，不同对角 => 一共有多少种摆法
 *
 * 1.先摆第一行第一列
 * 2.再摆第二行第一列，矛盾到第二列
 * 3.再摆第三行......
 *
 * @Author jieHFUT
 * @Create 2025/7/28 1:17
 * @Version 1.0
 */


public class Queue8 {

    // 定义一个 max 表示一共有多少个皇后
    int max = 8;

    // 定义数组 array, 保存皇后每一行的放置下标，比如 array = {0,4,7,5,2,6,1,3}
    int[] array = new int[max];

    // 一个双重集合，记录所有成功的摆放结果
    private List<List<Integer>> result = new ArrayList<>();

    // 记录一共有多少次成功放置的方法
    private int count = 0;

    // 记录一共有多少次放置冲突的次数
    private int conflictCount = 0;

    public int getConflictCount() {
        return conflictCount;
    }

    public int getCount() {
        return count;
    }

    public List<List<Integer>> getResult() {
        return result;
    }


    /**
     * 开启八皇后问题
     * @return
     */
    public List<List<Integer>> start() {
        addQueue(1);
        return getResult();
    }
    /**
     * 编写一个方法，放置第 N 个皇后
     * @param n
     */
    public void addQueue(int n) {
        if (n == max + 1) {
            // 前面已经放置了 8 个皇后，n = 9 时放的是第九个皇后，直接产生一个成功结果
            putResult();
            return;
        }
        // 在下标为 n - 1 的行放置皇后(依次放到每一列)
        for (int i = 0; i < max; i++) {
            array[n-1] = i;
            if (!isConflict(n)) {
                // 说明第 N 个皇后放在 i 列不产生冲突，放置第 N+1 个皇后
                addQueue(n + 1);
            } else {
                // 说明第 N 个皇后放在 i 列产生冲突，放到下一列

            }
        }
    }
    /**
     * 当我们放置第 N 个皇后的时候，判断其和前面的 N-1 个皇后是否有冲突
     * 前面放置的皇后在数组 array 中
     * 如果有冲突 return true，否则返回 false
     * @param n
     * @return
     */
    public boolean isConflict(int n) {
        conflictCount++;
        /**
         * 此时在数组 array 中，下标 0 - N-1 中都有前 N-1 个皇后的列数
         * 1.先判断第 N 个皇后和前面的 N-1 个皇后列数是否冲突
         * 2.再判断对角是否冲突
         */
        for (int i = 0; i < n - 1; i++) {
            if (array[n-1] == array[i]) {
                // 列冲突
                return false;
            }
            if (Math.abs(n-1-i) == Math.abs(array[n-1]-array[i])) {
                return false;
            }
        }
        return false;
    }

    /**
     * 将每一次成功的放置结果放到集合 result 中：List<List<Integer>> result
     * 并且记录成功放置的次数 count
     * @param
     */
    public void putResult() {
        count++;
        List<Integer> list = new ArrayList<>();
        for (int num : array) {
            list.add(num);
        }
        result.add(list);
    }

}
