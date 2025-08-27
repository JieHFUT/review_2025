package test;

/**
 * ClassName: SixtyNine
 * Package: test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/8/27 19:31
 * @Version 1.0
 */
public class SixtyNine {
    public static void main(String[] args) {

        System.out.println(mySqrt(Integer.MAX_VALUE));

    }


    public static int mySqrt1(int x) {
        // 计算算数平方根（思路：从 1 开始计算平方，直到出现第一个大于 x 的数字）

        int ret = 1;
        while(true) {
            // 问题出在当 ret * ret 溢出的时候就出现 bug

            if (ret * ret > x) {
                break;
            }
            ret++;
        }
        return ret - 1;
    }


    public static int mySqrt(int x) {
        // 计算算数平方根（思路：从 1 开始计算平方，直到出现第一个大于 x 的数字）
        // 对 ret 的初始值进行调整，减少运行时间
        int key = x / 100_000_000;
        int ret = 0;
        if (key < 1) {
            ret = 1;
        } else if (key >= 1 && key < 4) {
            ret = 10000;
        } else if (key >= 4 && key < 9) {
            ret = 20000;
        } else if (key >= 9 && key < 16) {
            ret = 30000;
        } else {
            ret = 40000;
        }

        while(true) {
            if (ret * ret > x) {
                break;
            }
            System.out.println(ret);
            ret++;
        }
        return ret - 1;
    }
}
