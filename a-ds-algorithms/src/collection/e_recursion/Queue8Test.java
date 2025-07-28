package collection.e_recursion;

import java.util.Arrays;
import java.util.List;

/**
 * ClassName: Queue8Test
 * Package: collection.e_recursion
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/7/28 13:49
 * @Version 1.0
 */
public class Queue8Test {
    public static void main(String[] args) {

        Queue8 queue8 = new Queue8();
        List<List<Integer>> start = queue8.start();
        for (List<Integer> list : start) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }


    }
}
