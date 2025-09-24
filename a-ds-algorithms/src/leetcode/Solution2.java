package leetcode;

import java.util.*;

/**
 * ClassName: Solution2
 * Package: test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/8/20 21:35
 * @Version 1.0
 */
public class Solution2 {

    public static void main(String[] args) {
//        List<List<String>> lists = groupAnagrams(new String[]{"ddddddddddg", "dgggggggggg"});
//        for (List<String> list : lists) {
//            System.out.println(list);
//        }

//        List<List<String>> lists = solveNQueens(4);
//        for (List<String> list : lists) {
//            System.out.println(list);
//        }

//        System.out.println(isMatch("aa", "a*"));

//        System.out.println(longestPalindrome("aaaa"));

//        System.out.println(isMatch10("ab", ".*"));

        System.out.println(longestValidParentheses(")()())"));

    }



    /////////////////////////////////32/////////////////////////////////////
    public static int longestValidParentheses(String s) {
        // 思路二：动态规划
        // dp[i][j] 代表字符串的 i 下标到 j 下标的子串是否有效
        // 如果 dp[i][j] 的两个端点是'(' && ')'，那么就看其内部是否是有效的
        // dp[i][j] = s.(i) == '(' && s.(j) === ')' && dp[i+1][j-1]
        // 边界条件：从长度为 1 的开始，都是 false; 然后是长度为 2 的
        int len = s.length();

        boolean[][] dp = new boolean[len][len];
        // 设置边界条件
        for (int i = 0; i < len; i++) {
            dp[i][i] = false;
        }

        char[] chars = s.toCharArray();
        int max_size = 0;

        // 针对每一种长度开始填充数据
        for (int l = 2; l <= len; l++) {
            // 从每一个下标开始找长度为 l 的子串
            for (int i = 0; i < len - l + 1; i++) {
                if (l == 2) {
                    // 长度为 2 地时候，由下标 i 指向 i+1
                    if (chars[i] == '(' && chars[i + 1] == ')') {
                        dp[i][i + 1] = true;
                        if (max_size < 2) max_size = 2;
                    } else {
                        dp[i][i + 1] = false;
                    }
                } else {
                    // start = i; end = i + l - 1
                    dp[i][i + l - 1] = chars[i] == '(' && chars[i + l - 1] == ')' && dp[i + 1][i + l - 2];
                    if (dp[i][i + l - 1] && max_size < l) {
                        max_size = l;
                    }
                }
            }
        }
        return max_size;
    }
    ///////////////////////////////////////10////////////////////////////////////////////
    public static boolean isMatch10(String s, String p) {
        // 使用动态规划
        // 寻找子问题：从后往前看（原问题 = 最右端末尾字符的匹配 + 剩余子串的匹配）
        // 对最右端进行分类讨论
        // 1. p的最右端是非'*'号的字符 => 如果最右端不匹配直接返回 false
        //                           => 如果最右端匹配就看下一个子问题
        // 2. p的最右端如果是p[i-1] = '*'号字符 => p(i-2) = s(i-1)
        //                                    => p(i-2)* 重复0次的话考察p(i-3) && s(i-1)
        //                                    => p(i-2)* 重复1次的话考察p(i-3) && s(i-2)
        //                    p(i-2) = s(i-2) => p(i-2)* 重复>=2次，考察p(i-1) && s(i-2)
        // 3. p的最右端如果是p[i-1] = '*'号字符 => p(i-2) != s(i-1)
        //                                    => 这样子的话就把 p(i-1)* 当成空的去考察 p(i-3) && s(i-1)

        // 代表 s 的前 i 个字符和 p 的前 j 个字符是不是匹配
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        // 情况一，两者都为空或者两者长度都为 0
        dp[0][0] = true;
        // 情况二，s 字符串不为空，但是 p 字符串为空
        // 也就是二维数组的第一列是 false，但是默认就是 false，不用管
        // 情况三，s 字符串为空，但是 p 字符串不为空（需要讨论）

        for (int j = 1; j < p.length(); j++) {
            if (p.charAt(j) == '*') {
                dp[0][j + 1] = dp[0][j - 1];
            }
        }

        ///////////////// s.length()=7   p.length()=6 ///////////////////////
        // 第一列是 S 字符串不为空，P 字符串为空； 第一行是 S 字符串为空，P 不为空
        //   0 1 2 3 4 5 6
        // 0 T F ? ? ? ? ?
        // 1 F
        // 2 F
        // 3 F
        // 4 F
        // 5 F
        // 6 F
        // 7 F

        // 填充数据在数组中
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                // 从上面空白的地方开始填写（这个地方的情况是 S 字符串的长度是 i, P 的字符串的长度是 j）
                // i 和 j 是从 1 开始的
                if (p.charAt(j - 1) != '*' && (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1))) {
                    dp[i][j] = dp[i-1][j-1];
                } else if (p.charAt(j - 1) != '*' && p.charAt(j - 1) != s.charAt(i - 1)) {
                    dp[i][j] = false;
                } else if (p.charAt(j - 1) == '*') {
                    // p 字符串的最后一个字符是  '*'，并且'*'号的前一个字符等于 s 字符串的最后一个字符
                    if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.') {

                        if (i >= 2 && (p.charAt(j - 2) == s.charAt(i - 2) || p.charAt(j - 2) == '.')) {
                            //          ?* 匹配 0 个         ?* 匹配 1 个        ?* 匹配 >= 2 个
                            dp[i][j] = dp[i][j - 2] || dp[i - 1][j - 2] || dp[i - 2][j];
                        } else {
                            dp[i][j] = dp[i][j - 2] || dp[i - 1][j - 2];
                        }

                    } else {
                        // 直接移除 ?*
                        dp[i][j] = dp[i][j - 2];
                    }
                }
            }
        }

        return dp[s.length()][p.length()];
    }
    ////////////////////////////5//////////////////////////////
    public static String longestPalindrome(String s) {
        // 思路二：动态规划
        // 子问题：P(i,j) 表示字符串的第 i个字母到第 j个字母组成的串是不是回文串
        // 子关联：P(i,j) = P(i+1,j-1) && S(i) == S(j)
        // 边界条件是什么？长度为1是回文串，长度为2的时候如果两个字符相同就是回文串
        int len = s.length();
        if (len < 2) return s;

        int max_size = 1;
        int begin_index = 0;
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] isPalindrome = new boolean[len][len];
        // 初始条件：所有长度为 1 的串都是回文串
        for (int i = 0; i < len; i++) {
            isPalindrome[i][i] = true;
        }

        // 开始遍历其他情况
        char[] chars = s.toCharArray();
        for (int i = 0; i < len; i++) {
            // i 代表的是子串的前面字符的下标
            for (int j = 0; j < len; j++) {
                // j 代表的是子串的后面字符的下标
                if (i >= j) continue;
                // 如果子串的长度为 2
                if (j == i + 1) {
                    boolean flag = (chars[i] == chars[j]);
                    isPalindrome[i][j] = flag;
                    if (max_size < 2 && flag) {
                        max_size = 2;
                        begin_index = i;
                    }
                    continue;
                }
                // 子串的长度大于等于3的时候，进行状态转移方程
                boolean flag = (chars[i] == chars[j]) && isPalindrome[i+1][j-1];
                isPalindrome[i][j] = flag;
                if ((j - i + 1) > max_size && flag) {
                    max_size = j -i + 1;
                    begin_index = i;
                }
            }
        }

        return s.substring(begin_index, begin_index + max_size);

    }
    ////////////////////////////10///////////////////////////////////////
    public static boolean isMatch(String s, String p) {

        if (s.length() == 0 && p.length() == 0) return true;
        if (p.length() == 0) return false;
        if (s.length() == 0) {
            if (p.length() == 1) return false;
            // 最后一个不是 '*'
            if (p.charAt(p.length() - 1) != '*') return false;
            // 长度大于等于二并且最后一个是 '*'
            return isMatch(s, p.substring(0, p.length() - 2));
        }

        int lens = s.length();
        int lenp = p.length();
        // 这里两个字符串的长度都不为 0
        // 1.p的最后一个字符不是 '*' 号
        if (p.charAt(lenp-1) != '*') {
            // 两个字符串比完最后一个字符后，同时扔掉最后一个字符
            return (p.charAt(lenp-1) == '.' || p.charAt(lenp-1) == s.charAt(lens-1))
                    && isMatch(s.substring(0, lens - 1), p.substring(0, lenp - 1));
        } else {
            // 2.p 的最后一个字符是 '*'
            char ch = p.charAt(lenp - 2);
            if (ch == s.charAt(lens - 1)) {
                // 三种情况成立一种即可
                return isMatch(s, p.substring(0, lenp - 2))
                        || isMatch(s.substring(0, lens - 1), p.substring(0, lenp - 2))
                        || isMatch(s.substring(0, lens - 1), p);
            } else {
                // 直接扔掉这一对带星号的两个字符
                return isMatch(s, p.substring(0, lenp - 2));
            }
        }
    }

    public static boolean isMatch1(String s, String p) {
        // 使用动态规划
        // 寻找子问题：从后往前看（原问题 = 最右端末尾字符的匹配 + 剩余子串的匹配）
        // 对最右端进行分类讨论
        // 1. p的最右端是非'*'号的字符 => 如果最右端不匹配直接返回 false
        //                           => 如果最右端匹配就看下一个子问题
        // 2. p的最右端如果是p[i-1] = '*'号字符 => p(i-2) = s(i-1)
        //                                    => p(i-2)* 重复0次的话考察p(i-3) && s(i-1)
        //                                    => p(i-2)* 重复1次的话考察p(i-3) && s(i-2)
        //                                    => p(i-2)* 重复>=2次，考察p(i-1) && s(i-2)
        // 3. p的最右端如果是p[i-1] = '*'号字符 => p(i-2) != s(i-1)
        //                                    => 这样子的话就把 p(i-1)* 当成空的去考察 p(i-3) && s(i-1)




        // 代表 s 的前 i 个字符和 p 的前 j 个字符是不是匹配
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        // 情况一，两者都为空
        dp[0][0] = true;
        // 情况二，s 字符串不为空，但是 p 字符串为空
        // 也就是二维数组的第一列是 false，但是默认就是 false，不用管
        // 情况三，s 字符串为空，但是 p 字符串不为空（需要讨论）

        for (int j = 1; j < p.length(); j++) {
            if (p.charAt(j) == '*') {
                dp[0][j + 1] = dp[0][j - 1];
            }
        }

        ///////////////// s.length()=7   p.length()=6 ///////////////////////
        // 第一列是 S 字符串不为空，P 字符串为空； 第一行是 S 字符串为空，P 不为空
        //   0 1 2 3 4 5 6
        // 0 T F ? ? ? ? ?
        // 1 F
        // 2 F
        // 3 F
        // 4 F
        // 5 F
        // 6 F
        // 7 F

        // 填充数据在数组中
        for (int i = 1; i < dp.length; i++) {
            for(int j = 1; j < dp[0].length; j++) {
                // 从上面空白的地方开始填写（这个地方的情况是 S 字符串的长度是 i, P 的字符串的长度是 j）
                if (p.charAt(j-1) != '*' && (p.charAt(j-1) == '.' || p.charAt(j-1) == s.charAt(i-1))) {
                    dp[i][j] = true;
                } else if (p.charAt(j-1) != '*' && p.charAt(j-1) != s.charAt(i-1)) {
                    dp[i][j] = false;
                } else if (p.charAt(j-1) == '*') {
                    if (p.charAt(j - 2) == s.charAt(i-1)) {
                        if (i < 2 && j >= 3) {
                            dp[i][j] = dp[i-1][j-3];
                        } else {
                            if(j < 3) {
                                dp[i][j] = dp[i-2][j-1];
                            } else {
                                dp[i][j] = dp[i-1][j-3] || dp[i-2][j-3] || dp[i-2][j-1];
                            }
                        }
                    } else {
                        dp[i][j] = dp[i-1][j-3];
                    }
                }
            }
        }

        return dp[s.length()][p.length()];
    }
    ////////////////////////////51////////////////////////////////////////////////
    public static List<List<String>> solveNQueens(int n) {

        // 每一行皇后的下标
        List<String> setIndex = new ArrayList<>();
        //
        addQueue(0, n, setIndex);
        return ret;
    }


    // 记录所有可能的结果，返回集合
    static List<List<String>> ret = new ArrayList<>();

    // 判断放在这个位置是否冲突
    public static boolean isConflict(List<String> setIndex, String str) {
        // 首先已经保证了其不在同一行
        int n = str.length();
        if (setIndex.size() == 0) return false;

        char[][] mitrix = new char[setIndex.size() + 1][n];
        for (int i = 0; i < mitrix.length; i++) {
            if (i != mitrix.length - 1) {
                mitrix[i] = setIndex.get(i).toCharArray();
            } else {
                mitrix[i] = str.toCharArray();
            }
        }
        // 验证不同列
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < mitrix.length; i++) {
            for (int j = 0; j < mitrix[i].length; j++) {
                if (mitrix[i][j] == 'Q') {
                    // 先判断列是否重复
                    if (map.containsValue(j)) return true;
                    // 判断是否有对角重复
                    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                        if (Math.abs(i - entry.getKey()) == Math.abs(j - entry.getValue())) {
                            return true;
                        }
                    }
                    map.put(i, j);
                }
            }
        }
        return false;
    }

    // x 是放置第 x+1 个皇后，setIndex 是前面放置的 x 个皇后的位置
    public static void addQueue(int x, int n, List<String> setIndex) {

        if (x == n) {
            // 已经放置了 n 个皇后，产生了一组结果
            ret.add(setIndex);
            return;
        }

        // 对于放置第 x+1 个皇后，需要依次将其放置 0 -> n-1 的位置
        for (int i = 0; i < n; i++) {
            char[] chars = new char[n];
            Arrays.fill(chars, '.');
            chars[i] = 'Q';
            String str = new String(chars);
            if (!isConflict(setIndex, str)) {
                List<String> nowIndex = new ArrayList<>();
                nowIndex.addAll(setIndex);
                nowIndex.add(str);
                addQueue(x + 1, n, nowIndex);
            } else {
                // 发生了冲突，就直接判断皇后放在下一个位置是否冲突

            }
        }

    }
    ///////////////////////////////////49////////////////////////////////////////
    public static List<List<String>> groupAnagrams(String[] strs) {
        // 思路：HashMap<HashSet<Character>, Integer>
        // 如果某一个 HashSet<Character> 集合被匹配到了，就去获取其异位下标

        HashMap<HashMap<Character, Integer>, Integer> hashmap = new HashMap<>();

        int index = 0;
        List<List<String>> lists = new ArrayList<>();

        for (int i = 0; i < strs.length; i++) {
            // 将该字符串放到 set 中（Integer 记录字符出现的次数）
            HashMap<Character, Integer> map = new HashMap<>();
            for (int j = 0; j < strs[i].length(); j++) {
                // 如果没有匹配到，就放入
                if(!map.containsKey(strs[i].charAt(j))){
                    map.put(strs[i].charAt(j), 1);
                } else {
                    map.put(strs[i].charAt(j), map.get(strs[i].charAt(j)) + 1);
                }
            }

            // 从 hashmap 中去匹配，如果没有匹配到，就创建一个数组，放到 arraylist 中去，同时记录其下标
            if (!hashmap.containsKey(map)) {
                hashmap.put(map, index);

                // 将这个字符串放到这个下标内
                List<String> every = new ArrayList<>();
                every.add(strs[i]);
                lists.add(every);
                index++;

            } else {
                // 匹配到了，将其缀在对应下标后面
                int toSet = hashmap.get(map);
                lists.get(toSet).add(strs[i]);
            }
        }
        return lists;
    }

    public static List<List<String>> groupAnagrams1(String[] strs) {
        // 思路：HashMap<HashSet<Character>, Integer>v
        // 如果某一个 HashSet<Character> 集合被匹配到了，就去获取其异位下标

        HashMap<HashSet<Character>, Integer> hashmap = new HashMap<>();

        int index = 0;
        List<List<String>> lists = new ArrayList<>();

        for (int i = 0; i < strs.length; i++) {
            // 将该字符串放到 set 中
            HashSet<Character> set = new HashSet<>();
            for (int j = 0; j < strs[i].length(); j++) {
                set.add(strs[i].charAt(j));
            }

            // 从 map 中去匹配，如果没有匹配到，就创建一个数组，放到 arraylist 中去，同时记录其下标
            if (!hashmap.containsKey(set)) {
                hashmap.put(set, index);

                // 将这个字符串放到这个下标内
                List<String> every = new ArrayList<>();
                every.add(strs[i]);
                lists.add(every);
                index++;

            } else {
                // 匹配到了，将其缀在对应下标后面
                int toSet = hashmap.get(set);
                lists.get(toSet).add(strs[i]);
            }
        }
        return lists;
    }




}
