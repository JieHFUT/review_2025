package algorithms.g_kruskal;

import java.util.Arrays;
import java.util.Comparator;

public class Kruskal {
    // 用来表示两个顶点不联通的情况
    public static final int INF = 65535;
    // 用来描述顶点信息
    private String[] vertexs;
    // 用来描述边与边之间的权值信息
    private int[][] weights;
    // 用来记录边的个数，用来给边排序
    private int linkNum;


    // 通过构造方法来进行初始化
    public Kruskal(String[] vertexs, int[][] weights) {
        int len = vertexs.length;
        // 初始化顶点信息
        this.vertexs = new String[len];
        for (int i = 0; i < len; i++) {
            this.vertexs[i] = vertexs[i];
        }
        // 初始化边的权值信息
        this.weights = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                this.weights[i][j] = weights[i][j];
            }
        }
        // 计算一共有多少条边
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (this.weights[i][j] != INF) {
                    this.linkNum++;
                }
            }
        }
    }

    // 打印邻接表
    public void print(){
        for (int i = 0; i < this.weights.length; i++) {
            for (int j = 0; j < this.weights[i].length; j++) {
                System.out.printf("%-7d", this.weights[i][j]);
            }
            System.out.println();
        }
    }

    // 获得所有的边 link[]
    public Link[] getLinks() {
        int index = 0;
        Link[] links = new Link[this.linkNum];
        for (int i = 0; i < this.weights.length; i++) {
            for (int j = i + 1; j < this.weights[i].length; j++) {
                if (this.weights[i][j] != INF) {
                    links[index++] = new Link(this.vertexs[i], this.vertexs[j], this.weights[i][j]);
                }
            }
        }
        return links;
    }
    // 给所有的边进行排序
    public void sortLinks(Link[] links) {
        Arrays.sort(links, new Comparator<Link>() {
            @Override
            public int compare(Link o1, Link o2) {
                return o1.weight - o2.weight;
            }
        });
    }

    // 获得某一个顶点对应的下标
    public int getIndex(String vertex) {
        for (int i = 0; i < this.vertexs.length; i++) {
            if (this.vertexs[i].equals(vertex)) {
                return i;
            }
        }
        return -1;
    }




    // 开始 kruskal 算法

    /**
     * 本算法最难的部分：找终点
     * 如果某个顶点有终点，返回终点下标；如果终点还有终点，就继续找终点
     *
     * 获取下标为 index 的节点的终点，用于判断某两个顶点的终点是否相同
     * @param ends 记录各个顶点的终点是哪个
     * @param index
     * @return
     */
    public int getDest(int[] ends, int index) {
        while (ends[index] != 0) {
            index = ends[index];
        }
        return index;
    }
    /**
     * 开始寻找一条路径可以通过所有的顶点
     */
    public Link[] kruskal() {
        // 判断每一个节点的终点的数组
        int[] ends = new int[this.vertexs.length];
        // 用来保存每次添加哪一条边的返回数组
        int index = 0;
        Link[] ret = new Link[this.vertexs.length - 1];
        // 获得排序好的所有的边的集合
        Link[] links = this.getLinks();
        sortLinks(links);

        // 获取排序中此时的最短边
        // 每次获得一条边，一共要获得 顶点数-1 条边，遍历所有的边，如果没有产生回路就将其添加到 ret 中
        for (int i = 0; i < this.linkNum; i++) {
            // 获得该边的两个顶点对应的下标
            int prev = getIndex(links[i].start);
            int post = getIndex(links[i].end);
            // 获得两个顶点对应的终点
            int prevDest = getDest(ends, prev);
            int postDest = getDest(ends, post);

            // 判断这条边是否会产生回路
            if (prevDest != postDest) {
                // prevDest != postDest 不会产生回路
                // 设置终点
                ends[prevDest] = postDest;
                // 将这条边返回到结果
                ret[index++] = links[i];
            }
        }
        return ret;
    }



}
