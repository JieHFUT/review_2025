package leetcode;

/**
 * ClassName: Question130
 * Package: test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/9/22 17:22
 * @Version 1.0
 */
public class Question130 {

    public static void main(String[] args) {

//        solve(new char[][]{{'X','X','X','X'},
//                           {'X','O','O','X'},
//                           {'X','X','O','X'},
//                           {'X','O','X','X'}});
        char[][] chars = new char[][]{{'O','X','O','O','O','O','O','O','O'},
                                      {'O','O','O','X','O','O','O','O','X'},
                                      {'O','X','O','X','O','O','O','O','X'},
                                      {'O','O','O','O','X','O','O','O','O'},
                                      {'X','O','O','O','O','O','O','O','X'},
                                      {'X','X','O','O','X','O','X','O','X'},
                                      {'O','O','O','X','O','O','O','O','O'},
                                      {'O','O','O','X','O','O','O','O','O'},
                                      {'O','O','O','O','O','X','X','O','O'}};
        solve(chars);


        System.out.println();


    }

    public static void solve(char[][] board) {
        // 围棋呗
        // 思路：对每一个圆圈进行回溯，如果能够到达边缘就不更改
        // 表示这个点有没有被参观过（参观过的点都被进行修正过）
        boolean[][] isVisited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 如果没有被参观过，并且值为 'O' 就进行判定
                if (isVisited[i][j] == false && board[i][j] == 'O') {

                    if (backtrack(i, j, board, isVisited) == false) {
                        // 如果这个点不能到达边缘，那么和其相邻的 'O' 也不能到达边缘
                        // 设置所有相邻的点值为 'O'
                        update(i, j, board);
                    } else {
                        // 如果这个点能到达边缘，那么和其相邻的 'O' 也能到达边缘
                        // 设置所有相邻的点值为已经参观
                        updateIsVisited(i, j, board, isVisited);
                    }

                }
            }
        }
    }


    // 如果这点能否到达边缘
    public static boolean backtrack(int row, int col, char[][] board, boolean[][] isVisited) {
        // 该点被参观过
        isVisited[row][col] = true;
        //
        if (row == 0 || col == 0 || row == board.length - 1 || col == board[0].length - 1) {
            // 这个点能够到达边缘
            return true;
        }

        // 右 - 下 - 左 - 上
        if (col + 1 < board[0].length && isVisited[row][col + 1] == false && board[row][col + 1] == 'O') {
            // 向右走能否到达边缘
            if (backtrack(row, col + 1, board, isVisited) == true) return true;
        }

        if (row + 1 < board.length && isVisited[row + 1][col] == false && board[row + 1][col] == 'O') {
            // 向下走能否到达边缘
            if (backtrack(row + 1, col, board, isVisited) == true) return true;
        }

        if (col - 1 >= 0 && isVisited[row][col - 1] == false && board[row][col - 1] == 'O') {
            // 向左走能否到达边缘
            if (backtrack(row, col - 1, board, isVisited) == true) return true;
        }

        if (row - 1 >= 0 && isVisited[row - 1][col] == false && board[row - 1][col] == 'O') {
            // 向上走能否到达边缘
            if (backtrack(row - 1, col, board, isVisited) == true) return true;
        }

        return false;
    }


    public static void update(int row, int col, char[][] board) {
        // 修改所有直接接触的
        board[row][col] = 'X';

        // 右 - 下 - 左 - 上
        if (col + 1 < board[0].length && board[row][col + 1] == 'O') {
            update(row, col + 1, board);
        }

        if (row + 1 < board.length && board[row + 1][col] == 'O') {
            update(row + 1, col, board);
        }

        if (col - 1 >= 0 && board[row][col - 1] == 'O') {
            update(row, col - 1, board);
        }

        if (row - 1 >= 0 && board[row - 1][col] == 'O') {
            update(row - 1, col, board);
        }
    }

    public static void updateIsVisited(int row, int col, char[][] board, boolean[][] isVisited) {
        // 修改所有直接接触的
        isVisited[row][col] = true;

        // 右 - 下 - 左 - 上
        if (col + 1 < board[0].length && isVisited[row][col + 1] == false && board[row][col + 1] == 'O') {
            updateIsVisited(row, col + 1, board, isVisited);
        }

        if (row + 1 < board.length && isVisited[row + 1][col] == false && board[row + 1][col] == 'O') {
            updateIsVisited(row + 1, col, board, isVisited);
        }

        if (col - 1 >= 0 && isVisited[row][col - 1] == false && board[row][col - 1] == 'O') {
            updateIsVisited(row, col - 1, board, isVisited);
        }

        if (row - 1 >= 0 && isVisited[row - 1][col] == false && board[row - 1][col] == 'O') {
            updateIsVisited(row - 1, col, board, isVisited);
        }
    }
}
