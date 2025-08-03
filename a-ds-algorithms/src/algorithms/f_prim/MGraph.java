package algorithms.f_prim;

/**
 * ClassName: MGraph
 * Package: algorithms.f_prim
 * Description:
 * 普里姆算法简介：
 *
 * 在一个图中构建最短的通路
 *
 * @Author jieHFUT
 * @Create 2025/8/3 17:05
 * @Version 1.0
 */
public class MGraph {
    int verxs;       // 表示图的节点个数
    String[] data;     // 存放结点数据，也就是节点名称
    int[][] weight;  // 存放边，就是我们的邻接矩阵


    public MGraph(int verxs) {
        this.verxs = verxs;
        data = new String[verxs];
        weight = new int[verxs][verxs];
    }
}
