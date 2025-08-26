package test;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: FiftyFour
 * Package: test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/8/25 15:12
 * @Version 1.0
 */
public class FiftyFour {

    public static void main(String[] args) {

        List<Integer> list = spiralOrder(new int[][]{{3}, {2}});
        System.out.println(list);
    }


    public static List<Integer> spiralOrder(int[][] matrix) {
        // 思路：创建一个布尔数组标志其是否已经被遍历过
        // 遇到被遍历的就进行转向，创建一个转向数组
        int rowl = matrix.length;
        int coll= matrix[0].length;
        boolean[][] isVisited = new boolean[rowl][coll];
        // 开始的时候 int index = 0，判断下一个往哪转向：int index = (0 + 1) % 4
        char[] turn = new char[]{'右', '下', '左', '上'};
        // 如果下一个是墙或者已经被遍历过就转向，如果转向后还是墙或者被遍历过就结束

        List<Integer> ret = new ArrayList<>();
        int row = 0, col = 0;
        int direct = 0;
        ret.add(matrix[row][col]);
        isVisited[row][col] = true;
        // 如果能够继续前进或者可以进行转向
        while (!isOver(row, col, direct, isVisited) || !isOver(row, col, (direct + 1) % 4, isVisited)) {
            // 下面是往某一个方向前进
            while (!isOver(row, col, direct, isVisited)) {
                // 需要继续前进
                switch(direct) {
                    case 0 :
                        // 向右前进（行不变，列加一）
                        ret.add(matrix[row][++col]);
                        isVisited[row][col] = true;
                        break;
                    case 1 :
                        // 向下前进（行加一，列不变）
                        ret.add(matrix[++row][col]);
                        isVisited[row][col] = true;
                        break;
                    case 2 :
                        // 向左前进（行不变，列减一）
                        ret.add(matrix[row][--col]);
                        isVisited[row][col] = true;
                        break;
                    case 3 :
                        // 向上前进（行减一，列不变）
                        ret.add(matrix[--row][col]);
                        isVisited[row][col] = true;
                        break;
                    default :
                        break;
                }
            }
            // 不能继续前进了，需要转向
            direct = (direct + 1) % 4;
        }
        return ret;
    }

    // 判断往 direct 方向能否继续前进
    public static boolean isOver(int row, int col, int direct, boolean[][] isVisited) {
        switch(direct) {
            case 0 :
                // 向右转向（行不变，列加一）
                if (col + 1 == isVisited[0].length || isVisited[row][col + 1] == true) {
                    return true;
                } else {
                    return false;
                }
            case 1 :
                // 向下转向（行加一，列不变）
                if (row + 1 == isVisited.length || isVisited[row + 1][col] == true) {
                    return true;
                } else {
                    return false;
                }
            case 2 :
                // 向左转向（行不变，列减一）
                if (col - 1 == -1 || isVisited[row][col - 1] == true) {
                    return true;
                } else {
                    return false;
                }
            case 3 :
                // 向上转向（行减一，列不变）
                if (row - 1 == -1 || isVisited[row - 1][col] == true) {
                    return true;
                } else {
                    return false;
                }
            default :
                return true;
        }
    }
}
