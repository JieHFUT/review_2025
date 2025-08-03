package algorithms.h_dijkstra;

import java.util.Arrays;

public class VisitedVertex {
    // 记录各个顶点是否访问过
    public int[] isVisited;
    // 记录在最短路径的路上，每一个顶点的前一个顶点的下标
    public int[] prev;
    // 记录从出发顶点到各个顶点已经确定的最短路径的值
    public int[] distance;


    /**
     * 通过构造器里初始化三个数组
     * @param count 顶点的数量
     * @param index 出发顶点的下标
     */
    public VisitedVertex(int count, int index) {
        // 除了出发顶点外，其他初始化为未访问过
        isVisited = new int[count];
        isVisited[index] = 1;
        // 除了出发顶点到出发顶点自己外，其余距离初始化为无限大
        distance = new int[count];
        Arrays.fill(distance, 65535);
        distance[index] = 0;
        // 初始化前驱顶点
        prev = new int[count];
    }

    // 在某一个顶点遍历结束，对起周边顶点的距离进行更新后，选择更新后最短距离的顶点，继续遍历
    public int getNew() {
        int min = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < isVisited.length; i++) {
            if (isVisited[i] == 0 && distance[i] < min) {
                min = distance[i];
                index = i;
            }
        }
        // 更新 index 顶点被访问过
        isVisited[index] = 1;
        return index;
    }

 }
