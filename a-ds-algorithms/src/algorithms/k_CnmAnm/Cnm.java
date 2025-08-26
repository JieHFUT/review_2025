package algorithms.k_CnmAnm;

/**
 * ClassName: Cnm
 * Package: algorithms.k_CnmAnm
 * Description:
 * 排列组合：C(n, k) = n! / (k! * (n - k)!)
 * @Author jieHFUT
 * @Create 2025/8/27 2:58
 * @Version 1.0
 */
public class Cnm {
    public static void main(String[] args) {


    }


    /**
     * 从 n 个元素中取出来 m 个元素有多少种取法
     * @param n
     * @param k
     * @return 这种使用阶乘的方法容易发生溢出问题
     */
    public static int cnm1(int n, int k) {
        if (k < 0 || k > n) return 0;
        if (k == 0 || k == n) return 1;
        return factorial(n) / (factorial(k) * factorial(n - k));
    }

    /**
     * 计算一个数字的阶乘
     * @param n
     * @return
     */
    public static int factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("计算阶乘不能为负数！");
        if (n == 1) return 1;
        return n * factorial(n - 1);
    }









    /**
     * 对其进行改进，让其不易发生溢出问题
     * C(n, k) = n! / (k! * (n - k)!
     *         = n*(n-1)*(n-2)...(n-k+1) / 1*2...*k
     * 上下有相同的数字 => (n-k+1)/1 * (n-k+2)/2 * ....
     * @param n
     * @param k
     * @return
     */
    public static int cnm2(int n, int k) {
        if (k < 0 || k > n) return 0;
        if (k == 0 || k == n) return 1;

        // C(n, k) = C(n, n-k)
        k = Math.min(k, n - k);

        int result = 1;
        for (int i = 1; i <= k; i++) {
            result *= (n-k+i) / i;
        }

        return result;
    }





    /**
     * 使用公式 帕斯卡恒等式: C(n, k) = C(n-1, k-1) + C(n-1, k)
     * @param n
     * @param k
     * @return
     */
    public static int cnm3(int n, int k) {
        if (k < 0 || k > n) return 0;
        if (k == 0 || k == n) return 1;

        return cnm3(n - 1, k - 1) + cnm3(n - 1, k);
    }

    // 带记忆化的递归计算 C(n, k)（提高效率）
    private static int[][] memo = new int[100][100];

    public static int combinationMemo(int n, int k) {
        if (k < 0 || k > n) return 0;
        if (k == 0 || k == n) return 1;
        if (memo[n][k] != 0) return memo[n][k];

        memo[n][k] = combinationMemo(n - 1, k - 1) + combinationMemo(n - 1, k);
        return memo[n][k];
    }


    /**
     * 使用动态规划（还是要用到公式 帕斯卡恒等式）
     * dp[i][j] = C(i, j)
     * @param n
     * @param k
     * @return
     */
    public static int cnm4(int n, int k) {
        if (k < 0 || k > n) return 0;
        if (k == 0 || k == n) return 1;

        int[][] dp = new int[n + 1][k + 1];
        //////////////////////////////////////////////
        // C(n, k) = C(n-1, k-1) + C(n-1, k) => 左上角和头顶上的
        //    [0] [1] [2] [3] [4] [5] [6] => i值
        // [0] 1   1   1   1   1   1   1
        // [1] 0   ?   ?   ?   ?   ?   ?
        // [2] 0   ?   ?   ?   ?   ?   ?
        // [3] 0   ?   ?   ?   ?   ?   ?
        // j值 C(6, 3)

        // 边界条件（第一行和第一列）
        for (int i = 0; i <= n; i++) {
            // 第一行全部为 1（从 i 中取出来 0 个）
            dp[0][i] = 1;
        }

        // 从 i 个元素中取出来 j 个元素
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
        return dp[n][k];
    }













































}
