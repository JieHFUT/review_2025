package collection.g_search.search_fibonacci;

import java.util.Arrays;

/**
 * ClassName: Fibonacci
 * Package: collection.g_search.search_fibonacci
 * Description:
 * 斐波那契查找（也是需要一个有序的数组）
 * 和差值查找类似，也是对 mid 进行处理，不过这次是按照斐波那契数列进行分割
 *
 *
 * @Author jieHFUT
 * @Create 2025/7/28 22:07
 * @Version 1.0
 */
public class Fibonacci {

    // 斐波那契数列 - 1000是有序数组的最长值
    public static int maxSize = 1000;

    /**
     * 构造斐波那契数列（1000之前的）
     * @return
     */
    public static int[] fibonacci() {
        int[] fib = new int[maxSize];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib;
    }

    /**
     * 通过斐波那契数列查找目标值
     * 1 1 2 3 5 8 11 19 30 49 79 ......
     * 假如有一个长度为 79 的数组，可以将其分位 （30-1）+ 1 +（49-1） 两段，中间那个就是 mid
     *                                         79 - 1
     * 1.先将数组构造成为长度符合斐波那契数列的长度（符合 f(k)-1 的长度）
     * 2.以后每次去权重 mid,就按照斐波那契值来取
     *
     * @param array
     * @param target
     * @return
     */
    public static int fibonacciSearch(int[] array, int target) {
        int low = 0;
        int high = array.length - 1;

        // 将 array 长度构造为 fib 长度
        int k = 0; // 第 k + 1 个斐波那契数字
        int[] fib = fibonacci();
        while (high > fib[k] - 1) {
            k++;
        }
        // 此时 fib[k]-1 >= array 最大的下标

        // 扩展数组长度符合斐波那契长度，不足长度的补充 0
        int[] fibonacci = Arrays.copyOf(array, fib[k] - 1);
        // 为了不影响寻找结果，将上述补充的 0 使用数组的最大值替代
        for (int i = array.length - 1; i < fibonacci.length; i++) {
            fibonacci[i] = array[high];
        }

        // 开始寻找目标值
        // 假设 fib[k]-1 == 79 - 1
        // 那么分割就是 30-1 1 49-1 == 中间这个1的下标就是fib[k-1]-1
        // 1 1 2 3 5 8 11 19 30 49 79 ====== fib[]
        // 0 0 1 2 4 7 10 18 29 48 78 ====== fib[]-1

        //  0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 ... 77  78
        // low                                           high

        //  0 1 2 3 4 5... 29 30 31 ...47 48 49...  77  78
        // low                            mid          high

        //  0 1 2 3 4 5 ...29 30 31 ... 47 48 49 ... 77 78
        // low                         high   low        high
        while (low <= high) {
            int mid = low + fib[k-1] - 1;
            if (target < fibonacci[mid]) {
                // 去左边去找
                high = mid - 1;
                k--;
            } else if (target > fibonacci[mid]) {
                // 去右边找，把右边的看作一个新的斐波那契（49 -> 78）
                // fib[k] = fib[k-1] + fib[k-2]
                //   79   =    49    +    30
                //
                low = mid + 1;
                k -= 2;
            } else {
                // 找到了
                if (mid <= high)
                    return mid;
                return high;
            }
        }
        return -1;
    }

}
