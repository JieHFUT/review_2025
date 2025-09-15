package test;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: NietySix
 * Package: test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/9/13 20:13
 * @Version 1.0
 */
public class NietySix {
    public static void main(String[] args) {

        numTrees(5);

    }

    public static int numTrees(int n) {
        // 可以简单理解为有多少种排列组合方式 1 2 3 4 5 6 ... n
        List<Integer> rest = new ArrayList<>();

        for(int i = 1; i <= n; i++) {
            rest.add(i);
        }

        backtrack(new ArrayList<>(), rest);

        return ret.size();
    }




    static List<List<Integer>> ret = new ArrayList<>();


    /**
     * @param used 已经使用的数字
     * @param rest 没有使用的数字
     */
    public static void backtrack(List<Integer> used, List<Integer> rest) {
        if (rest.size() == 0) {
            // 获取了一个有效结果
            ret.add(new ArrayList<>(used));
            return;
        }

        // 还没有结束
        for (int i = 0; i < rest.size(); i++) {
            // 将这个数字加到 used 中
            int num = rest.get(i);
            used.add(num);
            rest.remove(i);

            backtrack(used, rest);

            used.remove(used.size() - 1);
            rest.add(i, num);
        }
    }

}
