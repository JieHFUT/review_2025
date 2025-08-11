package algorithms.d_kmp;

import java.text.Format;

/**
 * ClassName: KMPMatch
 * Package: algorithms.d_kmp
 * Description:
 *
 * 通过 KMP 算法进行匹配
 * 思路：利用之前 i 匹配读取的内容，某一次匹配失败的时候保证 i 不回溯。只要移动 j 即可
 *
 * 1.通过一个另外的数组 next[] 来记录 dest 字符串内部后面字符需要跳跃的程度
 *   toMatch = "BBC ABCDAB ABCDABSDABDE"  dest = "ABCDABD"
 *   针对 dest 来说，记录其部分匹配值                  0000120
 *   即如果某一次匹配时候 dest 的某一个字符不匹配失败：对于 i 来说，i 需要移动的位数是：移动位数 = 已匹配的字符数 - 该字符对应的部分匹配值
 * 2.对 dest 如何计算部分匹配表
 *   例如：abcdab 来说
 *   其前缀：a,ab,abc,abcd,abcda
 *   其后缀：bcdab,cdab,dab,ab,b
 *   前后缀的最长相同部分 ab 的长度是 2，所以 dest 的第六位的匹配值就是 2
 *
 * @Author jieHFUT
 * @Create 2025/8/2 21:10
 * @Version 1.0
 */
public class KMPMatch {
    // 1.计算部分匹配值
    // 2.通过 KMP 算法开始遍历


    /**
     *
     * @param toMatch  str1
     * @param dest str2
     * @return 如果 toMatch 中出现了 dest，就返回第一次出现的首字母的下标
     *         否则就返回 -1
     */
    public int kmpMatch(String toMatch, String dest, int[] next) {
        // 开始通过 i 遍历 toMatch 字符串，注意因为匹配值的问题，可能一次跳多个
        for (int i = 0, j = 0; i < toMatch.length(); i++) {

            // 1.开始进行匹配：先任由 i++，在 toMatch 中找到与 dest 的首字母一样的字符下标

            // 3.如果某一刻不匹配了，记录前面已经匹配的最后一个字符的匹配值 j = next[j -1]
            while (j > 0 && toMatch.charAt(i) != toMatch.charAt(j)) {
                j = next[j -1];
            }


            // 2.然后 i 与 j 同时进行加一，期间如果找到了就直接返回下标，否则 3
            if (toMatch.charAt(i) == dest.charAt(j)) {
                // 如果匹配成功，j 就往前移动一位
                j++;
            }
            if (j == dest.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    /**
     * 获取一个字符串的匹配表
     * 其实本质上就是看一个字符串的前多少个和其后多少个是相同的
     * abcde8943652937865290138abcde => 匹配值：5
     * @param dest
     * @return int[]
     */
    public int[] kmpNext(String dest) {

        int[] next = new int[dest.length()];
        // 一共要计算 next.length 次匹配值
        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {
            // 2.找到了第一个和首字母相同的字符以后，再去判断下一个字符的时候：考虑到前一个字符是一样的
            //   j 要指向下一个，判断-如果指向下一个也一样，匹配值就继续加一，j 继续后移
            //   j 要指向下一个，判断-如果指向下一个不一样，就说明其最多只能和首字母一样：判断其是否和首字母一样

            // 3.如果连续好几个一样的，例：遇到3个一样的了，j 指向 3，也就是判断第四个和新来的这个一样吗
            //   如果也一样，那么其匹配值就是 4，j 也变成了 4，再去判断下一个
            //   如果不一样，那么 j 就要往回退，看前面三个里面有没有和这个新的一样的：退的规则就是j = next[j-1]
            if (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }

            // 1.先寻找到第一个和首字母相同的字符，其匹配值为 1，没找到之前都是 0
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }



}
