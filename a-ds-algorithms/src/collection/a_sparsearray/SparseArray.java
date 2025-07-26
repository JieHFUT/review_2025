package collection.a_sparsearray;

/**
 * ClassName: SparseArray
 * Package: collection.a_sparsearray
 * Description:
 * 一个棋盘上有部分棋子，还有很多是空的，如果使用最小的内存来记录这个棋盘的信息
 * 二维数组 => 稀疏数组：
 *
 * @Author jieHFUT
 * @Create 2025/7/26 15:12
 * @Version 1.0
 */
public class SparseArray {
    public static void main(String[] args) {
        // 先创建一个棋盘及其棋子：黑色棋子为 1，白色棋子为 2
        int[][] chessArr1 = new int[11][11];
        /**
         0 0 0 0 0 0 0 0 0 0 0
         0 0 1 0 0 0 0 0 0 0 0
         0 0 0 2 0 0 0 0 0 0 0
         0 0 0 0 2 0 0 0 0 0 0
         0 0 0 0 0 0 0 0 0 0 0
         0 0 0 0 0 0 0 0 0 0 0
         0 0 0 0 0 0 0 0 0 0 0
         0 0 0 0 0 0 0 0 0 0 0
         0 0 0 0 0 0 0 0 0 0 0
         0 0 0 0 0 0 0 0 0 0 0
         0 0 0 0 0 0 0 0 0 0 0
         */
        // 如图，棋盘上有三颗棋子
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[3][4] = 2;

        // 输出原有的二位数组
        System.out.println("输出原有的二维数组：");
        for(int i = 0; i < chessArr1.length; i++) {
            for(int j = 0; j < chessArr1[i].length; j++) {
                System.out.print(chessArr1[i][j] + " ");
            }
            System.out.println();
        }


        /**
         * 稀疏数组的实现过程是：行，列，值
         * 第一行记录的是棋盘的行数，列数，棋子数
         * 下面的行数记录的是每一个棋子所在的行数，列数，棋子代表的数值
         *
         * 1.遍历二维数组，获得棋子个数
         * 2.设置稀疏数组第一行数值
         * 3.在遍历一遍二维数组，填充稀疏数组剩余的行
         */
        // 1.遍历二维数组获取棋子个数
        int chessNums = 0;
        for(int i = 0; i < chessArr1.length; i++) {
            for(int j = 0; j < chessArr1[i].length; j++) {
                if(chessArr1[i][j] != 0) {
                    chessNums++;
                }
            }
        }
        // 2.设置系数数组的第一行
        int[][] sparseArr = new int[chessNums + 1][3];
        sparseArr[0][0] = chessArr1.length;
        sparseArr[0][1] = chessArr1[0].length;
        sparseArr[0][2] = chessNums;

        // 3.遍历二位数组，获取棋子填充
        int count = 0;
        for(int i = 0; i < chessArr1.length; i++) {
            for(int j = 0; j < chessArr1[i].length; j++) {
                if(chessArr1[i][j] != 0) {
                    count++;
                    // 非零为棋子，进行记录，一个棋子记录稀疏数组一行
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }


        // 输出系数数组
        System.out.println("输出稀疏数组：");
        for(int i = 0; i < sparseArr.length; i++) {
            for(int j = 0; j < sparseArr[i].length; j++) {
                System.out.print(sparseArr[i][j] + " ");
            }
            System.out.println();
        }


        /**
         * 将稀疏数组还原为棋盘
         * 1.获取棋盘的行与列创建二维数组
         * 2.遍历稀疏数组进行赋值
         */
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        for(int i = 1; i < sparseArr.length; i++) {
            // 赋值
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        // 输出原始数组
        System.out.println("输出原始数组：");
        for(int i = 0; i < chessArr2.length; i++) {
            for(int j = 0; j < chessArr2[i].length; j++) {
                System.out.print(chessArr2[i][j] + " ");
            }
            System.out.println();
        }


    }

}
