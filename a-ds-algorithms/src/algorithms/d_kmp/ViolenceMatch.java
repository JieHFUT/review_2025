package algorithms.d_kmp;

/**
 * ClassName: iolenceMatch
 * Package: algorithms.d_kmp
 * Description:
 * 字符串匹配问题：
 * 现在要判断 str1 是否含有 str2, 如果存在，就返回第一次出现的位置, 如果没有，则返回-1
 *
 * 1.暴力匹配算法
 * 2.KMP 算法
 * @Author jieHFUT
 * @Create 2025/8/1 21:39
 * @Version 1.0
 */
public class ViolenceMatch {


    /**
     *
     * @param toMatch  str1
     * @param dest str2
     * @return 如果 toMatch 中出现了 dest，就返回第一次出现的首字母的下标
     *         否则就返回 -1
     */
    public int violenceMatch(String toMatch, String dest) {
        /**
         * 思路：如果任意一个为 null || "" ||  toMatch 长度小于 dest 就返回 -1
         *      然后从开头开始遍历，不一样就从 toMatch 下一个字母开始遍历
         *      直到找到或者遍历完成
         */
        if (toMatch == null || toMatch.equals("") || dest == null || dest.equals("") || toMatch.length() < dest.length()) {
            return  -1;
        }
        int i = 0; // 用来遍历 toMatch 的下标
        int j = 0; // 用来遍历 dest 的下标

        for (i = 0; i < toMatch.length(); i++) {
            // 从第一个字母开始遍历比较
            // 记录当前的位置，如果这一遍遍历失败就从下一位再开始比较
            int record = i;
            while (i < toMatch.length() && j < dest.length()) {
                if (toMatch.charAt(i) == dest.charAt(j)) {
                    // 遇到一样的字母就比较下一个
                    i++;
                    j++;
                } else {
                    // 遇到不一样的说明这轮比较失败
                    i = record;
                    j = 0;
                    break;
                }
            }
            // 判断是否找到了
            if (j == dest.length()) {
                // 找到了
                return i;
            }
        }
        return -1;
    }

}
