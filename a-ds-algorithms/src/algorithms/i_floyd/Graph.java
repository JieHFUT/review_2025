package algorithms.i_floyd;

import java.util.Arrays;

/**
 * ClassName: Graph
 * Package: floyd
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/11/7 21:34
 * @Version 1.0
 */

// 创建图
public class Graph {
    // 存放顶点的数组
    String[] vertexs;
    // 维护的第一个表：用来保存距离的二维数组
    int[][] distance;
    // 维护的第二个表：用来记录前驱的二维数组
    int[][] prev;
    // 表示两个顶点不联通
    public static final int N = 65535;



    /**
     * 构造方法对其进行初始化
     * @param count 顶点数量
     * @param vertexs 顶点名称
     * @param distance 临接表
     */
    public Graph(int count, String[] vertexs, int[][] distance) {
        this.vertexs = vertexs;
        this.distance = distance;
        this.prev = new int[vertexs.length][vertexs.length];
        // 对前驱节点进行初始化
        for (int i = 0; i < count; i++) {
            Arrays.fill(this.prev[i], i);
        }
    }



    // 打印构造的用来记录
    // 1.用来记录实时最短距离的 distance 数组
    // 2.用来记录实时的前驱顶点的 prev 数组
    public void print() {
        for (int[] dis : distance) {
            System.out.println(Arrays.toString(dis));
        }
        System.out.println("下面是前驱顶点的情况：");
        for (int[] pre : prev) {
            System.out.println(Arrays.toString(pre));
        }
    }


    // 开始通过弗洛伊德算法寻找各个路径
    // 前驱顶点 ==> 中间顶点 ==> 终点
    public void floyd() {
        // 每个顶点都要充当一遍中间顶点，一共 vertexs.length 个
        for (int k = 0; k < this.vertexs.length; k++) {
            // 这个中间顶点周边的前驱顶点
            for (int i = 0; i < this.vertexs.length; i++) {
                // 这个中间顶点周边的后继顶点
                for (int j = 0; j < this.vertexs.length; j++) {
                    // 原本记录的前驱顶点到后继顶点的距离 i->j = init
                    int init = distance[i][j];
                    // 前驱顶点通过该中转顶点到后继顶点的距离 i->k->j = len
                    int len = distance[i][k] + distance[k][j];
                    if (len < init) {
                        // 对前驱顶点到后继顶点的距离进行修改
                        distance[i][j] = len;
                        // 对起前驱顶点数组进行修改
                        prev[i][j] = prev[k][j];
                    }
                }
            }
        }

        // 输出结果
        result();
    }


    public void result() {
        for (int i = 0; i < distance.length; i++) {
            for (int j = i; j < distance.length; j++) {
                System.out.printf("%s到%s的最短距离为:%d\t", vertexs[i], vertexs[j], distance[i][j]);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(vertexs[i] + "=>");
                int m = i;
                int n = j;
                while(m < distance.length && prev[n][m] != prev[n][prev[n][m]]) {
                    String path = vertexs[prev[n][m]];
                    stringBuilder.append(path + "=>");
                    m = prev[n][m];
                }
                stringBuilder.append(vertexs[j]);
                System.out.printf("路径为:%s\n", stringBuilder);
                System.out.println("====================================");
            }
        }
    }



}
