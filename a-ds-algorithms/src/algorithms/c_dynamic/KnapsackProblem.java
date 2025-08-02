package algorithms.c_dynamic;

/**
 * ClassName: KnapsackProblem
 * Package: algorithms.c_dynamic
 * Description:
 * 背包问题：背包问题分为 01背包和完全背包
 * 01背包是指不能重复，完全背包是指可以重复
 *
 * 可以使用动态规划的思想来解决问题：通过填表的方式来进行解决
 *
 * @Author jieHFUT
 * @Create 2025/8/1 1:58
 * @Version 1.0
 */
public class KnapsackProblem {

    public static void main(String[] args) {

        // 建立一个数组，表示物品的重量
        int[] weight = {1,3,5,3,6,4,7,5};
        // 建立一个数组，表示物品的价值
        int[] value = {100, 200, 150, 300, 400, 180, 320, 260};
        // 背包的容量
        int capacity = 18;
        // 物品的个数
        int number = value.length;

        // 在前 i 个物品中能够装入容量为 j 的最大价值
        int[][] maxValue = new int[number+1][capacity+1];
        // 为了记录在背包容量为某一个值的时候 => 放的商品的种类和个数
        int[][] path = new int[number+1][capacity+1];

        // 初始化 maxValue 表格
        for (int i = 0; i < maxValue.length; i++) {
            // 设置其第一列全部是 0
            maxValue[i][0] = 0;
        }
        for (int i = 0; i < maxValue[0].length; i++) {
            // 设置其第一列全部是 0
            maxValue[0][i] = 0;
        }


        // 然后开始填表
        for (int i = 1; i < maxValue.length; i++) {
            // 一行一行开始处理，外层循环一次就是处理一行，i 就是现在添加到的是第几个物品
            for (int j = 1; j < maxValue[0].length; j++) {
                // 对每一个 maxValue 进行处理，j 就是现在背包的容量是多少
                // 如果新出现的这个物品比目前的背包容量还大
                if (weight[i-1] > j) {
                    // 那么这个容量下还是由前面哪些物品组成
                    maxValue[i][j] = maxValue[i-1][j];
                } else {
                    // 否则就比较该容量下使用原物品的最大值 ? 和新增加一个这中物品的最大值
                    int orgin = maxValue[i-1][j];
                    int now = maxValue[i-1][j-weight[i-1]] + value[i-1];
                    if (orgin > now) {
                        // 不采用这个新的物品
                        maxValue[i][j] = orgin;
                    } else {
                        // 采用，放一个这种新的物品
                        maxValue[i][j] = now;
                        // 放了就记录
                        path[i][j] = 1;
                    }
                }
            }
        }



        System.out.println("背包表价值表");
        int count = 1;
        System.out.println("-----1----2----3----4----5----6----7----8----9----10---11---12---13---14---15---16---17---18   ");
        for (int[] ints : maxValue) {
            for (int num : ints) {
                System.out.printf("%-5d", num);
            }
            System.out.println();
        }

        System.out.println("各个背包容量里物品的添加情况");
        for (int[] ints : path) {
            for (int num : ints) {
                System.out.printf("%-5d", num);
            }
            System.out.println();
        }
        System.out.println("背包里最终存放的物品为");
        int x = path.length - 1;
        int y = path[0].length - 1;
        while (x >= 0 && y >= 0) {
            if (path[x][y] == 1) {
                System.out.printf("第%d个物品放进背包中\n", x);
                y -= weight[x-1];
            }
            x--;
        }





    }

}
