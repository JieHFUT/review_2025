package collection.n_graph;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * ClassName: Graph
 * Package: graph
 * Description:
 * 顶点：vertex
 * 边：edge
 * 路径：
 * 无向图：
 * 有向图
 * 带权图：也称为网
 * 表示方法：
 * 1. 邻接矩阵 -> 二维数组
 * 2. 邻接表 -> 链表
 * @Author jieHFUT
 * @Create 2024/11/4 0:37
 * @Version 1.0
 */

public class Graph {

    // 图节点名称的集合
    private List<String> vertexList;
    // 边的连接表示
    public int[][] edges;
    // 边的数量
    public int numOfEdge;
    // 记录某一个节点是否被访问过
    public boolean[] isVisited;

    // 构造器
    public Graph(int numOfVertex) {
        // 初始化
        isVisited = new boolean[numOfVertex];
        edges = new int[numOfVertex][numOfVertex];
        vertexList = new ArrayList<String>(numOfVertex);
        numOfEdge = 0;
    }

    // 插入节点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 无向图添加边
     * @param from 开始节点
     * @param to 指向节点
     * @param weight 权重
     */
    public void insertEdge(int from, int to, int weight) {
        edges[from - 1][to - 1] = weight;
        edges[to - 1][from - 1] = weight;
        numOfEdge++;
    }



    // 返回某一个顶点的名称
    public String getValueByIndex(int index) {
        return vertexList.get(index);
    }
    // 返回顶点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }
    // 返回边的数量
    public int getNumOfEdge() { return numOfEdge; }
    // 返回两个节点之间的权值
    public int getWeight(int from, int to) {
        return edges[from][to];
    }


    // 打印矩阵
    public void printGraph() {
        for (int[] vertexOfEdge : edges) {
            System.out.println(Arrays.toString(vertexOfEdge));
        }
    }


    /////////////////////////////////////////////////////////////////////////////////////

    // 查找第一个邻接节点的下标
    public int getFirstNeighbor(int vertex) {
        for (int i = 0; i < edges[vertex - 1].length; i++) {
            if (edges[vertex - 1][i] > 0) {
                // 说明该几点找到了相连接的节点
                return i + 1;
            }
        }
        return -1;
    }

    // 根据前一个邻接节点的下标来获取下一个邻接节点
    public int getNextNeighbor(int vertex, int prevNeighbor) {
        for (int i = prevNeighbor; i < edges[vertex - 1].length; i++) {
            if (edges[vertex - 1][i] > 0) {
                return i + 1;
            }
        }
        return -1;
    }

    // 深度优先遍历算法
    public void dfs() {
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i]) {
                dfs(i + 1, isVisited);
            }
        }
        System.out.println();
    }
    public void dfs(int vertex, boolean[] isVisited) {
        // 第一次就是第一个节点 vertex = 1
        isVisited[vertex - 1] = true;
        System.out.print(getValueByIndex(vertex - 1) + " ");
        int neighbor = getFirstNeighbor(vertex);
        while (neighbor != -1) {
            // 有邻接节点
            if (!isVisited[neighbor - 1]) {
                dfs(neighbor, isVisited);
            } else {
                neighbor = getNextNeighbor(vertex, neighbor);
            }
        }
    }




    // 广度优先遍历 使用一个队列保持访问过的节点的顺序
    public void bfs() {
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i + 1);
            }
        }
        System.out.println();
    }
    public void bfs(boolean[] isVisited, int vertex) {
        // 队列，用来记录访问节点的顺序
        LinkedList<Integer> queue = new LinkedList();
        // 打印当前节点
        System.out.print(getValueByIndex(vertex - 1) + " ");
        isVisited[vertex - 1] = true;
        // 当前节点入队列
        queue.addLast(vertex);
        while (!queue.isEmpty()) {
            int current = queue.removeFirst();
            // 获取第一个邻接节点
            int neighbor = getFirstNeighbor(current);

            while (neighbor != -1) {
                if (!isVisited[neighbor - 1]) {
                    // 当前节点的邻接节点没被 visited
                    System.out.print(getValueByIndex(neighbor - 1) + " ");
                    // 邻接节点入队列
                    queue.addLast(neighbor);
                    isVisited[neighbor - 1] = true;
                } else {
                    // 当前节点这个邻接节点被 visited
                    int nextNeighbor = getNextNeighbor(current, neighbor);
                    neighbor = nextNeighbor;
                }
            }
            // 当前节点没有邻接节点 return
        }
    }

}
