package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: SeventyNine
 * Package: test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/8/29 10:40
 * @Version 1.0
 */
public class SeventyNine {


    public static void main(String[] args) {

        System.out.println(exist(new char[][]{{'A', 'B', 'C', 'E'},
                                              {'S', 'F', 'C', 'S'},
                                              {'A', 'D', 'E', 'E'}}, "ABCCED"));

    }



    public static boolean exist(char[][] board, String word) {
        // 思路：遍历二维数组，匹配到第一个字符（每次匹配到第一个字符就开始扩展，直到扩展到长度为 word.length()）
        List<Character> rest = new ArrayList<>();
        for (int i = 1; i < word.length(); i++) {
            rest.add(word.charAt(i));
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (word.length() == 1) return true;
                    boolean[][] isVisited = new boolean[board.length][board[0].length];
                    isVisited[i][j] = true;
                    backtrack(board, isVisited, i, j, rest);
                }
            }
        }

        return isHave;
    }


    static boolean isHave = false;
    /**
     * @param board
     * @param isVisited 标志这个位置有没有途经
     * @param row 目前处于的行下标
     * @param col 目前处于的列下标
     * @param rest 还需要途经的字符集合
     */
    public static void backtrack(char[][] board, boolean[][] isVisited, int row, int col, List<Character> rest) {
        if (rest.size() == 0) {
            isHave = true;
            return;
        }

        // 1.假设向右走能走的通
        if (col + 1 < board[0].length && isVisited[row][col + 1] == false && board[row][col + 1] == rest.get(0)) {
            // 右侧能够匹配成功
            isVisited[row][col + 1] = true;
            char remove = rest.remove(0);
            backtrack(board, isVisited, row, col + 1, rest);
            if (isHave == true) {
                return;
            } else {
                isVisited[row][col + 1] = false;
                rest.add(0, remove);
            }
        }

        // 2.假设向下走能够走的通
        if (row + 1 < board.length && isVisited[row + 1][col] == false && board[row + 1][col] == rest.get(0)) {
            // 下面能够匹配成功
            isVisited[row + 1][col] = true;
            char remove = rest.remove(0);
            backtrack(board, isVisited, row + 1, col, rest);
            if (isHave == true) {
                return;
            } else {
                isVisited[row + 1][col] = false;
                rest.add(0, remove);
            }
        }

        // 3.假设向左走能够走的通
        if (col - 1 >= 0 && isVisited[row][col - 1] == false && board[row][col - 1] == rest.get(0)) {
            // 左侧能够匹配成功
            isVisited[row][col - 1] = true;
            char remove = rest.remove(0);
            backtrack(board, isVisited, row, col - 1, rest);
            if (isHave == true) {
                return;
            } else {
                isVisited[row][col - 1] = false;
                rest.add(0, remove);
            }
        }


        // 3.假设向上走能够走的通
        if (row - 1 >= 0 && isVisited[row - 1][col] == false && board[row - 1][col] == rest.get(0)) {
            // 上侧能够匹配成功
            isVisited[row - 1][col] = true;
            char remove = rest.remove(0);
            backtrack(board, isVisited, row - 1, col, rest);
            if (isHave == true) {
                return;
            } else {
                isVisited[row - 1][col] = false;
                rest.add(0, remove);
            }
        }

    }
}
