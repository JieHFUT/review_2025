package leetcode;

/**
 * ClassName: FourtyFour
 * Package: test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/8/24 17:59
 * @Version 1.0
 */
public class FourtyFour {

    public static void main(String[] args) {
        System.out.println(isMatch("adceb", "*a*b"));
    }

    public static boolean isMatch(String s, String p) {
        // 1.使用动态规划（从后往前匹配）
        // dp[i][j] 表示字符串S的前i位是否和字符串P的前j位匹配
        // （1）如果字符串p的最后一位是非*号，dp[i][j] = dp[i-1][j-1] && s(i-1) == p(j-1)
        //  (2) 如果字符串p的最后一位是*号，那么分为几种：* 匹配零个字符 dp[i][j] = dp[i][j-1]
        //      如果字符串p的最后一位是*号，那么分为几种：* 匹配一个字符 dp[i][j] = dp[i-1][j-1]
        //      如果字符串p的最后一位是*号，那么分为几种：* 匹配多个字符 dp[i][j] = dp[i-2][j]

        // 边界条件：
        int lens = s.length();
        int lenp = p.length();
        char[] chars = s.toCharArray();
        char[] charp = p.toCharArray();
        boolean[][] dp = new boolean[lens + 1][lenp + 1];
        // 初始化
        /////////////////////////////////////////////
        //   0 1 2 3 4 5 6
        // 0 T ? ? ? ? ? ?
        // 1 F
        // 2 F
        // 3 F
        // 4 F
        // 两个字符串长度都是零的时候符合
        dp[0][0] = true;
        // s字符串长度不为零，p字符串的长度为零（也就是第一列为 false）
        // s字符串长度为零，p字符串的长度不为零（也就是第一列）
        for (int i = 0; i < lenp; i++) {
            if (charp[i] == '*') {
                dp[0][i + 1] = true;
            } else {
                break;
            }
        }

        // 开始动态规划
        for (int i = 0; i < lens; i++) {
            for(int j = 0; j < lenp; j++) {
                // 长度为 i+1   j+1  => 从bp[1][1] 开始的
                if (charp[j] != '*') {
                    dp[i+1][j+1] = dp[i][j] && (charp[j] == '?' || chars[i] == charp[j]);
                } else {
                    // p 的最后位置字符为 '*'
                    if (i > 0) {
                        // 可以匹配 0 1 多个
                        dp[i+1][j+1] = dp[i+1][j] || dp[i][j] || dp[i-1][j+1];
                    } else {
                        // 可以匹配 0 1
                        dp[i+1][j+1] = dp[i+1][j] || dp[i][j];
                    }
                }
            }
        }

        return dp[lens][lenp];
    }
}
