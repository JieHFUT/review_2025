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
 * 有向图：
 * 带权图：也称为网
 * 表示方法：
 * 1. 邻接矩阵 -> 二维数组
 *    图中有 N 个顶点，二维数组的行和列就都有 N 个元素,arr[i][j]为零代表有指向，为零代表没有指向
 * 2. 邻接表 -> 链表
 *    图中有 N 个顶点，就有 N 条链表，这些链表可以放在一个数组中
 *    0:1->2->3->
 *    1:0->4->
 *    2:0->4->5->
 *    3:0->5->
 *    4:0->1->2->5->
 *    5:2->3->4->
 * 图的遍历：
 *     1.深度优先遍历
 *     2.广度优先遍历
 *
 * @Author jieHFUT
 * @Create 2024/11/4 0:37
 * @Version 1.0
 */

public class Graph {

    // 图的顶点名称的集合
    public List<String> vertices;
    // 图的边的连接表示
    public int[][] edges;
    // 边的数量
    public int numOfEdge;
    // 记录某一个节点是否被访问过
    public boolean[] isVisited;

    // 构造器
    public Graph(int numOfVertices) {
        // 进行初始化
        isVisited = new boolean[numOfVertices];
        edges = new int[numOfVertices][numOfVertices];
        vertices = new ArrayList<>(numOfVertices);
        numOfEdge = 0;
    }

    /**
     * 插入顶点名称
     * @param vertex
     */
    public void insertVertex(String vertex) {
        vertices.add(vertex);
    }

    /**
     * 给无向图添加边
     * @param from 开始节点
     * @param to 指向节点
     * @param weight 权重
     */
    public void insertEdge(int from, int to, int weight) {
        edges[from - 1][to - 1] = weight;
        edges[to - 1][from - 1] = weight;
        numOfEdge++;
    }


    /**
     * 返回某一个顶点的名称
     * @param index
     * @return
     */
    public String getNameByInex(int index) {

    }




}
