package collection.k_huffman.huffman2_code;

public class Node implements Comparable<Node>{
    // 数据
    Byte value;
    // 权重
    int weight;
    // 左右节点
    Node left;
    Node right;

    public Node(Byte value, int weight) {
        this.value = value;
        this.weight = weight;
    }

    // 遍历方法
    public void preOrder() {
        System.out.println("该节点的权重是: " + this.weight);
        if (this.left != null)
            this.left.preOrder();
        if (this.right != null)
            this.right.preOrder();
    }


    @Override
    public String toString() {
        return "Node [value=" + value + ", weight=" + weight + "]";
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}
