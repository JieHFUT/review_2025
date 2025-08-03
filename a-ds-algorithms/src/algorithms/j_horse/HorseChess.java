package algorithms.j_horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 以马走日的方法
 * 跳满棋盘
 * 思路：实际上是图的深度优先的一种应用
 */

public class HorseChess {
    public static void main(String[] args) {
        System.out.println("马踏棋盘开始运行~~~");
        int X = 8; //棋盘的列数
        int Y = 8; //棋盘的行数
        int row = 1; //从哪一行开始出发
        int col = 1; //从哪一列开始出发
        HorseChess hc = new HorseChess(X, Y);
        hc.horseChess(row - 1, col - 1, 1);
        for (int[] aRow : hc.chessBoard) {
            System.out.println(Arrays.toString(aRow));
        }
    }












    // 马踏棋盘算法

    // 棋盘的列数
    private static int X;
    // 棋盘的行数
    private static int Y;
    // 棋盘实体
    int[][] chessBoard;
    // 记录各个位置是否被访问过
    private boolean[] isVisited;
    // 记录棋盘是否全部都被访问过
    private boolean isFinished;
    // 用于记录结果
    // 构造方法来进行初始化
    public HorseChess(int X, int Y) {
        this.X = X;
        this.Y = Y;
        this.chessBoard = new int[Y][X];
        this.isVisited = new boolean[X * Y];
    }

    /**
     *
     * @param row 开始的行下标
     * @param col 开始的列下标
     * @param step 这个位置是第几步走的
     */
    public void horseChess(int row, int col, int step) {
        // 首先记录 step 在棋盘中对应的点
        chessBoard[row][col] = step;
        // 记录开始的点是 isVisited
        isVisited[row * X + col] = true;
        // 获得开始点能走的下一步的点的集合（点 = 行，列）
        ArrayList<Point> nextPoints = next(new Point(col, row));

        sort(nextPoints);
        // 遍历该点的下一个点
        while (!nextPoints.isEmpty()) {
            // 挨个拿出集合中的点
            Point next = nextPoints.remove(0);
            // 如果这个点没有被访问过，就继续遍历
            if (!isVisited[next.y * X + next.x]) {
                horseChess(next.y, next.x, step + 1);
            } else {
                // 这个点被访问过
            }
        }
        //判断马儿是否完成了任务，使用 step 和应该走的步数比较 ，
        //如果没有达到数量，则表示没有完成任务，将整个棋盘置0
        //说明: step < X * Y  成立的情况有两种
        // 1. 棋盘到目前位置,仍然没有走完
        // 2. 该点周边的点全部走完，棋盘处于一个回溯过程
        if (step < X * Y && !isFinished) {
            // 说明没有走到最后一步或者正在回溯，但是没有成功
            // 记录该点不行
            chessBoard[row][col] = 0;
            isVisited[row * X + col] = false;
        } else {
            isFinished = true;
        }
    }


    /**
     * 功能： 根据当前位置(Point对象)，计算马儿还能走哪些位置(Point)，并放入到一个集合中(ArrayList), 最多有8个位置
     * @param curPoint
     * @return
     */
    public static ArrayList<Point> next(Point curPoint) {
        ArrayList<Point> pointList = new ArrayList<>();
        // 判断这个点的周边的点是否可以加入到集合中
        // 创建一个Point
        Point point = new Point();
        //表示马儿可以走5这个位置
        if((point.x = curPoint.x - 2) >= 0 && (point.y = curPoint.y -1) >= 0) {
            pointList.add(new Point(point));
        }
        //判断马儿可以走6这个位置
        if((point.x = curPoint.x - 1) >=0 && (point.y=curPoint.y-2)>=0) {
            pointList.add(new Point(point));
        }
        //判断马儿可以走7这个位置
        if ((point.x = curPoint.x + 1) < X && (point.y = curPoint.y - 2) >= 0) {
            pointList.add(new Point(point));
        }
        //判断马儿可以走0这个位置
        if ((point.x = curPoint.x + 2) < X && (point.y = curPoint.y - 1) >= 0) {
            pointList.add(new Point(point));
        }
        //判断马儿可以走1这个位置
        if ((point.x = curPoint.x + 2) < X && (point.y = curPoint.y + 1) < Y) {
            pointList.add(new Point(point));
        }
        //判断马儿可以走2这个位置
        if ((point.x = curPoint.x + 1) < X && (point.y = curPoint.y + 2) < Y) {
            pointList.add(new Point(point));
        }
        //判断马儿可以走3这个位置
        if ((point.x = curPoint.x - 1) >= 0 && (point.y = curPoint.y + 2) < Y) {
            pointList.add(new Point(point));
        }
        //判断马儿可以走4这个位置
        if ((point.x = curPoint.x - 2) >= 0 && (point.y = curPoint.y + 1) < Y) {
            pointList.add(new Point(point));
        }
        return pointList;
    }


    //根据当前这一步的所有的下一步的选择位置，进行非递减排序, 减少回溯的次数
    public static void sort(ArrayList<Point> ps) {
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                // TODO Auto-generated method stub
                //获取到o1的下一步的所有位置个数
                int count1 = next(o1).size();
                //获取到o2的下一步的所有位置个数
                int count2 = next(o2).size();
                if(count1 < count2) {
                    return -1;
                } else if (count1 == count2) {
                    return 0;
                } else {
                    return 1;
                }
            }

        });
    }





}
