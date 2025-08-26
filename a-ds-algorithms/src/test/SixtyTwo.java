package test;

/**
 * ClassName: SixtyTwo
 * Package: test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/8/26 23:29
 * @Version 1.0
 */
public class SixtyTwo {

    public static void main(String[] args) {

        System.out.println(combinationOptimized(5, 3));

    }






    public static long combinationOptimized(int n, int k) {
        if (k < 0 || k > n) return 0;
        if (k == 0 || k == n) return 1;

        // 使用对称性: C(n, k) = C(n, n-k)
        k = Math.min(k, n - k);

        long result = 1;
        for (int i = 1; i <= k; i++) {
            result = result * (n - k + i) / i;
        }
        return result;
    }
















    public static int uniquePaths2(int m, int n) {
        // 递归
        if (m == 1 && n == 1) {
            return 0;
        } else if (m == 1) {
            return 1;
        } else if (n == 1) {
            return 1;
        } else {
            return uniquePaths2(m, n - 1) + uniquePaths2(m - 1, n);
        }
    }




















    public static int uniquePaths1(int m, int n) {
        // 思路：回溯（M行N列）
        getPath(0, 0, m, n);
        return uniquePaths;
    }

    static int uniquePaths = 0;

    // row(0->m-1) 和 col(0->n-1) 是目前处于的位置，m 和 n 是棋盘的大小
    public static void getPath(int row, int col, int m, int n) {
        // 记录截止情况
        if (row == m - 1 && col == n - 1) {
            uniquePaths++;
            return;
        }



        if (row == m - 1) {
            // 只能向右走
            getPath(row, col + 1, m, n);
            return;
        }

        if(col == n - 1) {
            // 只能向下走
            getPath(row + 1, col, m, n);
            return;
        }

        // 既可以向下走，也可以向右走
        getPath(row, col + 1, m, n);
        getPath(row + 1, col, m, n);
    }

}
