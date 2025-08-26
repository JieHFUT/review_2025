package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName: Sixty
 * Package: test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/8/26 17:54
 * @Version 1.0
 */
public class Sixty {

    public static void main(String[] args) {

    }


    public String getPermutation(int n, int k) {
        // 思路一：回溯
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        arrange(list, "", k);
        return ret;
    }


    int count = 0;
    String ret = "";
    /**
     *
     * @param rest 还剩下的数字集合
     * @param str 已经使用的数字形成的字符串
     */
    public void arrange(List<Integer> rest,  String str, int k) {
        // rest = List[1, 2, 3, 4, 5... n]
        if (rest.size() == 0) {
            // 找到一个结果
            if (++count == k) {
                // 找到了需要被返回的数据
                ret = str;
            }
        }

        // 还没有遍历完
        for (int i = 0; i < rest.size(); i++) {
            // 这次需要添加的数据是 rest.get(i)
            int remove = rest.remove(i);
            arrange(rest, str + remove, k);
            rest.add(i, remove);

            if (ret.length() != 0) {
                break;
            }
        }

    }
}
