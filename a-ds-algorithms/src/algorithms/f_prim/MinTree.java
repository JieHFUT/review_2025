package algorithms.f_prim;

/**
 * ClassName: MinTree
 * Package: algorithms.f_prim
 * Description:
 * 思路：解决图中的最短通路的问题，使用普里姆算法，构造一个最小生成树 MST
 *
 * @Author jieHFUT
 * @Create 2025/8/3 17:05
 * @Version 1.0
 */
public class MinTree {



    /**
     * 创造图的临接矩阵
     * @param graph 图对象
     * @param verxs 图对应的顶点个数
     * @param data  图的各个顶点的值，也就是节点名称
     * @param weight 图的邻接矩阵，各个顶点之间的距离
     */
    public void createGraph(MGraph graph, int verxs, String data[], int[][] weight) {
        int i, j;
        for(i = 0; i < verxs; i++) { // 顶点
            graph.data[i] = data[i];
            for(j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    /**
     * 开始构建最小生成树
     * begin：开始的节点下标
     */
    public void prim(MGraph graph, int begin) {
        // 用于记录某节点是否已经被连接
        int[] visited = new int[graph.verxs];
        // 从 begin 节点开始
        visited[begin] = 1;

        // m 和 n 是用于保存某一轮中涉及到的两个顶点，用来记录最短路径两头的节点下标
        int m = -1;
        int n = -1;


        // 由于有 n 个节点，就需要找到 n-1 条边，也就是要遍历 n-1 遍
        for (int i = 1; i < graph.verxs; i++) {
            // 记录一轮中最短路径的值
            int toFindMinPath = PrimTest.INF;
            // 在已经被使用过的顶点和没有被使用过的顶点之间找到一条最短路径
            /**
             {INF,5,7,INF,INF,INF,2},
             {5,INF,INF,9,INF,INF,3},
             {7,INF,INF,INF,8,INF,INF},
             {INF,9,INF,INF,INF,4,INF},
             {INF,INF,8,INF,INF,5,4},
             {INF,INF,INF,4,5,INF,6},
             {2,3,INF,INF,4,6,INF}};

             {1,1,0,0,1,0,0}
             */
            // 遍历整个临接表，找到没有被使用的的节点，并且其有路径
            for (int j = 0; j < graph.verxs; j++) {
                for (int k = 0; k < graph.verxs; k++) {
                    // 如果 j 这个顶点被选择过，并且遍历的 k 节点没有被选择过
                    // 而且这两个节点之间路径的长度小于 toFindMinPath，修改 toFindMinPath
                    if (visited[i] == 1 && visited[k] == 0 && graph.weight[j][k] < toFindMinPath) {
                        toFindMinPath = graph.weight[j][k];
                        m = j;
                        n = k;
                    }
                }
            }
            // 找到了一条最短的路径
            System.out.printf("从%s到%s 路径长度：%d\n", graph.data[m], graph.data[n], toFindMinPath);
            visited[n] = 1;
            m = -1;
            n = -1;
        }
    }



}
