package collection.n_graph;


import java.util.*;

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
    public List<String> vertix;
    // 图的边的连接表示
    public int[][] edges;
    // 边的数量
    public int numOfEdge;
    // 记录某一个节点是否被访问过
    public boolean[] isVisited;

    // 构造器（传入顶点数量）
    public Graph(int numOfVertices) {
        // 进行初始化
        isVisited = new boolean[numOfVertices];
        edges = new int[numOfVertices][numOfVertices];
        vertix = new ArrayList<>(numOfVertices);
        numOfEdge = 0;
    }

    /**
     * 插入顶点名称
     * @param vertex
     */
    public void insertVertex(String vertex) {
        vertix.add(vertex);
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
        return vertix.get(index);
    }

    /**
     * 返回顶点的个数
     * @return
     */
    public int getNumberOfVertix() {
        return vertix.size();
    }

    /**
     * 返回边的数量
     * @return
     */
    public int getNumberOfEdges() {
        return numOfEdge;
    }

    /**
     * 返回两个节点之间的权值
     * @param from
     * @param to
     * @return
     */
    public int getWeight(int from, int to) {
        return edges[from][to];
    }


    /**
     * 打印二维矩阵
     */
    public void printGraph() {
        for (int[] row : edges) {
            System.out.println(Arrays.toString(row));
        }
    }



    ////////////////////////////////////////////////////////////////////
    ////// 1.DFS：深度优先遍历
    ////// 2.BFS：广度优先遍历
    ////////////////////////////////////////////////////////////////////

    /**
     * 查找下标为 index 节点的第一个邻接节点的下标
     * @param index
     * @return 如果存在就返回下标，否则就返回 -1
     */
    public int getFirstNeighour(int index) {
        for (int i = 0; i < vertix.size(); i++) {
            if (edges[index][i] > 0) {
                // 找到了 vertex 的第一个节点
                return i;
            }
        }
        return -1;
    }

    /**
     * 对 index 节点来说，根据其前一个节点来获取其下一个节点的下标
     * @param index 节点下标
     * @param preNeighour 前一个临接节点的下标
     * @return 下一个临接节点的下标
     */
    public int getNextNeighbour(int index, int preNeighour) {
        for (int i = preNeighour + 1; i < vertix.size(); i++) {
            if (edges[index][i] > 0) {
                // 找到了下一个节点
                return i;
            }
        }
        return -1;
    }


    /**
     * 深度优先遍历算法
     * 从当前节点开始访问，如果当前节点有下一个节点并且下一个节点没被访问过，那就访问下一个节点，并重复
     *                 如果当前节点有下一个节点但是下一个节点已被访问过，那就再看当前节点的下下个节点
     *                 一直到当前节点连接的节点全部过一遍，再从下一个节点开始（如果下一个节点已经被访问过，再直接下一个）
     *
     * 思路：
     * 1.访问初始结点 v，并标记结点 v为已访问。
     * 2.查找结点 v的第一个邻接结点 w。
     * 3.若 w 存在，则继续执行 4
     *   若 w 不存在，则回到第 1步，将从 v 的下一个结点继续。
     * 4.若 w 未被访问，对 w进行深度优先遍历递归（即把 w当做另一个 v，然后进行步骤 123）。
     *   若 w 已被访问，查找结点 v的 w 邻接结点的下一个邻接结点，转到步骤 3。
     */
    public void dfs() {
        // 每次深度优先遍历前都将该数组重置
        isVisited = new boolean[vertix.size()];
        // 如果从第一个借点的临接链表结束了一遍，再走下一个节点的临接链表（前提是这个节点还没有被访问过）
        for (int i = 0; i < vertix.size(); i++) {
            if (!isVisited[i]) {
                dfs(i, isVisited);
            }
        }
    }

    /**
     *
     * @param index 当前节点的下标
     * @param isVisited
     */
    public void dfs(int index, boolean[] isVisited) {
        // 如果 index == 0，传递的就是第一个节点

        // 设置节点已经被访问
        isVisited[index] = true;
        System.out.println(getNameByInex(index) + "->");
        // 查找其第一个临接节点
        int w = getFirstNeighour(index);
        while (w != -1) {
            // 有临接节点，需要判断这个临接节点是不是回溯回来的
            if (!isVisited[w]) {
                // 没有被访问过，不是回溯来的
                dfs(w, isVisited);
            } else {
                // 已经被访问过，再去寻找下一个
                w = getNextNeighbour(index, w);
            }
        }
    }


    /**
     * 广度优先遍历
     * （前提：对没有 visited 的节点遍历）
     * （从第一个节点开始找其临接节点，有就输出，没有就找下一个 => 再根据找的顺序重复第一步）
     * （从第二个节点开始找其临接节点，有就输出，没有就找下一个 => 再根据找的顺序重复第一步）
     * （从第三个节点开始找其临接节点，有就输出，没有就找下一个 => 再根据找的顺序重复第一步）
     *  .........
     *
     *
     * 1.访问初始结点 v并标记结点 v为已访问。
     * 2.结点 v入队列
     * 3.当队列非空时，继续执行，否则算法结束。
     * 4.出队列，取得队头结点 u。
     * 5.查找结点 u的第一个邻接结点 w。
     * 6.若结点 u的邻接结点 w不存在，则转到步骤 3；否则循环执行以下三个步骤：
     *     6.1 若结点 w尚未被访问，则访问结点 w并标记为已访问。
     *     6.2 结点 w入队列
     *     6.3 查找结点 u的继 w邻接结点后的下一个邻接结点 w，转到步骤 6。
     *
     */
    public void bfs() {
        // 每次广度优先遍历前都将该数组重置
        isVisited = new boolean[vertix.size()];
        // 如果从第一个借点的临接链表结束了一遍，再走下一个节点的临接链表（前提是这个节点还没有被访问过）
        for (int i = 0; i < vertix.size(); i++) {
            if (!isVisited[i]) {
                bfs(i, isVisited);
            }
        }
    }

    private void bfs(int i, boolean[] isVisited) {
        int u; // 表示队列的头节点对应的下标
        int w; // 表示临接节点的下标
        // 一个队列，用于记录节点访问的顺序
        Queue<Integer> queue = new LinkedList<>();
        // 1.访问节点并且输出节点信息
        System.out.println(getNameByInex(i) + "->");
        isVisited[i] = true;
        queue.add(i);

        while (!queue.isEmpty()) {
            // 获得头节点的下标
            u = queue.poll();
            // 寻找该节点的临接节点
            w = getFirstNeighour(u);
            while (w != -1) {
                // 有临接节点
                if (!isVisited[w]) {
                    isVisited[w] = true;
                    System.out.println(getNameByInex(w) + "->");
                    queue.add(w);
                }
                // 以 u 为前驱节点，找到 w 后面的下一个临接节点
                w = getNextNeighbour(u, w);
            }
        }
    }


}
