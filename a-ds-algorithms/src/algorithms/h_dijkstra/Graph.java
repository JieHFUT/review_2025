package algorithms.h_dijkstra;

import java.util.Arrays;

/**
 * ClassName: Graph
 * Package: dijkstra
 * Description:
 * 思路：通过广度优先遍历来实现
 *
 * {N,5,7,N,N,N,2}
 * {5,N,N,9,N,N,3}
 * {7,N,N,N,8,N,N}
 * {N,9,N,N,N,4,N}
 * {N,N,8,N,N,5,4}
 * {N,N,N,4,5,N,6}
 * {2,3,N,N,4,6,N}
 *
 * 1.dist[] 数组：用来记录出发顶点到各个顶点的最路路径长度
 *   dist[N, N, N, N, N, N, 0]
 * 2.prev[] 数组：记录访问的前驱节点
 *   prev[0, 0, 0, 0, 0, 0, 0]
 * 3.isVisited[] 数组，各个顶点是否被访问过
 *   isVisited[0, 0, 0, 0, 0, 0, 1]
 *
 * 4.然后开始从该节点开始访问，如果到某节点的距离比 dis 中记录的小，就更换
 *   同时改变的记录其前驱节点
 *   (1) 访问完 G 点到其他节点以后
 *       dist[2, 3, N, N, 4, 6, 0]
 *       prev[6, 6, 0, 0, 6, 6, 0]
 *       isVi[0, 0, 0, 0, 0, 0, 1]
 *   (2) 再访问下一个顶点（根据广度优先去选择 A 去走一遍）
 *       dist[]
 *
 *
 *
 *
 *
 *
 *
 *
 * @Author jieHFUT
 * @Create 2024/11/6 23:27
 * @Version 1.0
 */
public class Graph {
    // 顶点数组名称
    String[] vertexs;
    // 邻接矩阵
    int[][] weights;
    // 已经访问的顶点的集合
    VisitedVertex visitedVertex;

    // 构造器
    public Graph(String[] vertexs, int[][] weights) {
        this.vertexs = vertexs;
        this.weights = weights;
    }

    // 显示邻接矩阵
    public void print() {
        for (int[] links : weights) {
            System.out.println(Arrays.toString(links));
        }
    }

    // 开始进行迪杰斯特拉算法实现   begin：表示从哪个顶点开始寻找最短路径
    public void dijkstra(int begin) {
        // 获得三个数组
        visitedVertex = new VisitedVertex(vertexs.length, begin);
        // 根据开始节点更新
        update(begin);
        // 遍历之后的 vertexs.length -1 顶点
        for (int i = 1; i < vertexs.length; i++) {
            // 得到在 update 中某一个顶点遍历结束拥有最短距离的那个顶点
            int theNew = visitedVertex.getNew();
            update(theNew);
        }
        showResult(begin);
    }

    // 根据某一个顶点，在遍历其周边顶点的过程中，更新到某一个顶点的最小值，并且设置前驱顶点
    // 也就是更新 distance 数组
    public void update(int index) {
        // len 就是我们要去寻找的某一个节点的最短路径
        // len = 出发顶点到 index 的最短路径 + min{ 从index遍历时，index 到 i 的距离}
        int len = 0;
        // 遍历这个顶点周围的顶点
        for (int i = 0; i < weights[index].length; i++) {
            len = visitedVertex.distance[index] + weights[index][i];
            // 如果该节点 i 没有被访问过，并且len < 从开始节点到 i 的距离
            if (visitedVertex.isVisited[i] == 0
                    && len < visitedVertex.distance[i]){
                // 更新出发节点到该节点 i 的距离
                visitedVertex.distance[i] = len;
                // 更新 i 顶点的前驱为 index
                visitedVertex.prev[i] = index;
            }
        }
    }

    // 提供一个输出结果的方法
    // isVisited prev distance
    // 输出形如：F(8)：A=>G=>F
    public void showResult(int begin) {
        for (int i = 0; i < visitedVertex.isVisited.length; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(vertexs[i]);
            stringBuilder.append("(");
            stringBuilder.append(visitedVertex.distance[i]);
            stringBuilder.append(")：");
            int index = i;
            StringBuilder stringBuilder1 = new StringBuilder();
            while (visitedVertex.prev[index] != begin) {
                stringBuilder1.append(new StringBuilder(vertexs[visitedVertex.prev[index]]).reverse());
                stringBuilder1.append(">=");
                index = visitedVertex.prev[index];
            }
            stringBuilder.append(vertexs[begin]);
            if (visitedVertex.prev[begin] != 0) {
                stringBuilder.append("=>");
            }
            // 加入中间路径
            stringBuilder.append(stringBuilder1.reverse());

            stringBuilder.append("=>");
            stringBuilder.append(vertexs[i]);
            System.out.println(stringBuilder);
        }
    }


}
