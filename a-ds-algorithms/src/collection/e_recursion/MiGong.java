package collection.e_recursion;

/**
 * ClassName: MiGong
 * Package: collection.e_recursion
 * Description: 如何找到在迷宫中到达目标地点的最短路径
 *
 *
 * @Author jieHFUT
 * @Create 2025/7/28 1:17
 * @Version 1.0
 */
public class MiGong {
    public static void main(String[] args) {
        // 迷宫大小
        int[][] miGong = new int[8][10];
        // 对迷宫进行边界设定
        for (int i = 0; i < miGong.length; i++) {
            miGong[i][0] = 1;
            miGong[i][miGong[0].length - 1] = 1;
        }
        for (int i = 0; i < miGong[0].length; i++) {
            miGong[0][i] = 1;
            miGong[miGong.length - 1][i] = 1;
        }
        // 一些障碍
        miGong[2][0] = 1;
        miGong[2][1] = 1;
        miGong[2][2] = 1;
        miGong[2][3] = 1;
        miGong[4][4] = 1;
        miGong[4][6] = 1;
        miGong[4][7] = 1;
        miGong[6][3] = 1;
        miGong[7][3] = 1;
        // 显示迷宫
        System.out.println("显示迷宫：");
        for (int i = 0; i < miGong.length; i++) {
            for (int j = 0; j < miGong[0].length; j++) {
                System.out.print(miGong[i][j] + " ");
            }
            System.out.println();
        }

        // 开始寻找路径，如果路能走通返回 true, 否则返回 false
        boolean isWay = getWay(1, 1, miGong, 6, 8);
        System.out.println(isWay);

        // 显示迷宫通路
        System.out.println("显示迷宫通路：");
        for (int i = 0; i < miGong.length; i++) {
            for (int j = 0; j < miGong[0].length; j++) {
                System.out.print(miGong[i][j] + " ");
            }
            System.out.println();
        }


    }


    /**
     * 开始寻找路径，如果路能走通返回 true, 否则返回 false
     * @param i 出发点行
     * @param j 出发点列
     * @param miGong
     * @param m 目的地行
     * @param n 目的地列
     * @return
     *
     * 1.如果某一点能找到到目的地的通路就设置为 2，返回真
     * 2.如果该点没有走过就是 0，1 代表墙，3 代表该点走不通
     * 3.策略：下右上左
     *
     *
     */
    public static boolean getWay(int i, int j, int[][] miGong, int m, int n) {
        // 通路已经找到
        if (miGong[m][n] == 2) return true;
        if (miGong[i][j] == 0) {
            // 该点没有走过，开始测试：下右上左
            miGong[i][j] = 2; // 假设该点可以走通
            if (getWay(i + 1, j, miGong, m, n)) {
                // 向下走能走得通
                return true;
            } else if (getWay(i, j + 1, miGong, m, n)) {
                // 向右走能走得通
                return true;
            } else if (getWay(i - 1, j, miGong, m, n)) {
                // 向上走能走得通
                return true;
            } else if (getWay(i, j - 1, miGong, m, n)) {
                // 向左走能走通
                return true;
            } else {
                // 这个点走不通
                miGong[i][j] = 3;
                return false;
            }
        } else {
            // 这个点已经走过了或者是墙
            return false;
        }
    }
}
