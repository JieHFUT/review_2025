package leetcode;

/**
 * ClassName: SixtyFour
 * Package: test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/8/27 15:03
 * @Version 1.0
 */
public class SixtyFour {

    public static void main(String[] args) {
        System.out.println(minPathSum1(new int[][]{ {3,8,6,0,5,9,9,6,3,4,0,5,7,3,9,3},
                                                    {0,9,2,5,5,4,9,1,4,6,9,5,6,7,3,2},
                                                    {8,2,2,3,3,3,1,6,9,1,1,6,6,2,1,9},
                                                    {1,3,6,9,9,5,0,3,4,9,1,0,9,6,2,7},
                                                    {8,6,2,2,1,3,0,0,7,2,7,5,4,8,4,8},
                                                    {4,1,9,5,8,9,9,2,0,2,5,1,8,7,0,9},
                                                    {6,2,1,7,8,1,8,5,5,7,0,2,5,7,2,1},
                                                    {8,1,7,6,2,8,1,2,2,6,4,0,5,4,1,3},
                                                    {9,2,1,7,6,1,4,3,8,6,5,5,3,9,7,3},
                                                    {0,6,0,2,4,3,7,6,1,3,8,6,9,0,0,8},
                                                    {4,3,7,2,4,3,6,4,0,3,9,5,3,6,9,3},
                                                    {2,1,8,8,4,5,6,5,8,7,3,7,7,5,8,3},
                                                    {0,7,6,6,1,2,0,3,5,0,8,0,8,7,4,3},
                                                    {0,4,3,4,9,0,1,9,7,7,8,6,4,6,9,5},
                                                    {6,5,1,9,9,2,2,7,4,2,7,2,2,3,7,2},
                                                    {7,1,9,6,1,2,7,0,9,6,6,4,4,5,1,0},
                                                    {3,4,9,2,8,3,1,2,6,9,7,0,2,4,2,0},
                                                    {5,1,8,8,4,6,8,5,2,4,1,6,2,2,9,7}  }));
    }







    public static int minPathSum1(int[][] grid) {
        // 1.考虑使用递归
        return minPathSum(grid, 0, 0);
    }
    // 下标 (row, col) 到达终点的最短路径长度
    public static int minPathSum(int[][] grid, int row, int col) {
        // 在终点
        if (row == grid.length - 1 && col == grid[0].length - 1) {
            return grid[grid.length - 1][grid[0].length - 1];
        } else if (row == grid.length - 1) {
            // 在最后一行
            return grid[row][col] + minPathSum(grid, row, col + 1);
        } else if (col == grid[0].length - 1) {
            // 在最后一列
            return grid[row][col] + minPathSum(grid, row + 1, col);
        } else {
            return Math.min(grid[row][col] + minPathSum(grid, row, col + 1),
                    grid[row][col] + minPathSum(grid, row + 1, col));
        }
    }















    public static int minPathSum(int[][] grid) {
        // 2.考虑使用回溯
        getAllPathSum(grid, 0, 0, 0);
        return minans;
    }

    static int minans = Integer.MAX_VALUE;
    /**
     * @param grid
     * @param row  目前处于的行下标
     * @param col  目前处于的列下标
     * @param sumprev 前面遍历的数据的合，不包含(row, col)
     */
    public static void getAllPathSum(int[][] grid, int row, int col, int sumprev) {

        sumprev += grid[row][col];

        // 考虑终止条件
        if (row == grid.length - 1 && col == grid[0].length - 1) {
            // 到达终点
            if (sumprev < minans) {
                minans = sumprev;
            }
            return;
        }


        // 还没有到达终点
        if (row == grid.length - 1) {
            // 到达最后一行，只能向右前进
            getAllPathSum(grid, row, col + 1, sumprev);
        } else if (col == grid[0].length - 1) {
            // 已经到达了最后一列，只能向下前进
            getAllPathSum(grid, row + 1, col, sumprev);
        } else {
            // 可以下可以右
            getAllPathSum(grid, row, col + 1, sumprev);
            getAllPathSum(grid, row + 1, col, sumprev);
        }
    }
}
