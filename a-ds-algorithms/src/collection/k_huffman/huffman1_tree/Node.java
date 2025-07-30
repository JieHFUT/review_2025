package collection.k_huffman.huffman1_tree;

/**
 * ClassName: Node
 * Package: huffmantree
 * Description:
 * 该树的带权路径长度(wpl)达到最小，称这样的二叉树为最优二叉树，也称为哈夫曼树
 *
 * 路径长度：从根节点到该节点的路径有多少条边
 * 权值：叶子节点的权重
 * 带权路径长度(wpl)：(求和：路径长度*权值)
 *
 * @Author jieHFUT
 * @Create 2024/10/30 23:23
 * @Version 1.0
 */
public class Node implements Comparable<Node>{

    public int value;

    public Node left;
    public Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node [value=" + value + "]";
    }

    // 按照从小到大排序
    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }

    // 前序遍历
    public void preOrder() {
        System.out.println(this.value);

        if (this.left != null)
            this.left.preOrder();
        if (this.right != null)
            this.right.preOrder();
    }

}
