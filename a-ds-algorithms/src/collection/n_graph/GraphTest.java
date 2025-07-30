package collection.n_graph;

public class GraphTest {
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        // 构造无向图的顶点
        String[] vertexs = {"1.北京", "2.天津", "3.苏州", "4.上海", "5.深圳"};
        for (int i = 0; i < vertexs.length; i++) {
            graph.insertVertex(vertexs[i]);
        }
        // 构造无向图的边
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,5,1);
        graph.insertEdge(2,3,1);
        graph.insertEdge(2,4,1);
        graph.insertEdge(3,4,1);
        // 打印矩阵
        System.out.println("无向图矩阵为: ");
        graph.printGraph();
        System.out.println("深度优先遍历结果为：");
        graph.dfs();

        System.out.println("广度优先遍历结果为：");
        graph.bfs();
    }
}
