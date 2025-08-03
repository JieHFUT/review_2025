package algorithms.g_kruskal;


import static algorithms.g_kruskal.Kruskal.INF;

public class KruskalTest {
    public static void main(String[] args) {
        // 构建顶点信息
        String[] vertexs = {"A地", "B地", "C地", "D地", "E地", "F地", "G地"};
        // 描述边与边之间的权值
        int weights[][] = {
                       /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {   0,  12, INF, INF, INF,  16,  14},
                /*B*/ {  12,   0,  10, INF, INF,   7, INF},
                /*C*/ { INF,  10,   0,   3,   5,   6, INF},
                /*D*/ { INF, INF,   3,   0,   4, INF, INF},
                /*E*/ { INF, INF,   5,   4,   0,   2,   8},
                /*F*/ {  16,   7,   6, INF,   2,   0,   9},
                /*G*/ {  14, INF, INF, INF,   8,   9,   0}};

        Kruskal kruskal = new Kruskal(vertexs, weights);
        // 尝试打印邻接表
        kruskal.print();
        // 获取所有的边的信息
        Link[] links = kruskal.getLinks();
        // 给所有的边进行排序
        kruskal.sortLinks(links);
        System.out.println("排序后的边为：");
        for (Link link : links) {
            System.out.println(link);
        }
        // 开始进行 kruskal 算法
        Link[] result = kruskal.kruskal();

        for (Link link : result) {
            System.out.printf("从%s到%s，路径长%d\n", link.start, link.end, link.weight);
        }
    }
}
