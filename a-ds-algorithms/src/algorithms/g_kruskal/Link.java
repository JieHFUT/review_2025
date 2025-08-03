package algorithms.g_kruskal;


/**
 * 这是边的类，用来描述一条边的信息
 */
public class Link {
    // 边的起点
    public String start;
    // 边的终点
    public String end;
    // 边的权值
    public int weight;

    public Link(String start, String end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Link{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}
