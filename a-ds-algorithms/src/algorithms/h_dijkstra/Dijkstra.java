package algorithms.h_dijkstra;

/**
 * ClassName: Dijkstra
 * Package: dijkstra
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/11/6 23:27
 * @Version 1.0
 */
public class Dijkstra {
    // 用来表示两个点之间不联通
    public static final int N = 65535;

    public static void main(String[] args) {
        // 所有顶点的名称
        String[] vertexs = {"A地", "B地", "C地", "D地", "E地", "F地", "G地"};
        // 各个顶点之间的权值
        int[][] weights = new int[vertexs.length][vertexs.length];
        weights[0]=new int[]{N,5,7,N,N,N,2};
        weights[1]=new int[]{5,N,N,9,N,N,3};
        weights[2]=new int[]{7,N,N,N,8,N,N};
        weights[3]=new int[]{N,9,N,N,N,4,N};
        weights[4]=new int[]{N,N,8,N,N,5,4};
        weights[5]=new int[]{N,N,N,4,5,N,6};
        weights[6]=new int[]{2,3,N,N,4,6,N};

        // 创建图对象
        Graph graph = new Graph(vertexs, weights);
        // 寻找到某一个顶点到其他顶点的最短路径
        graph.dijkstra(2);

    }
}
















