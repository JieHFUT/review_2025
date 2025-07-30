package collection.k_huffman.huffman1_tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ClassName: HuffmanTree
 * Package: collection.k_huffman.huffman1_tree
 * Description:
 * 如何构造霍夫曼树（权值越大应该距离根节点越近）
 *
 * 1.将一个数组从小到大进行排序，初始将每一个数都看成一个最简单的二叉树
 * 2.选择最小的两个组成一个二叉树，该二叉树根节点的权值是这两个数的和
 * 3.将上面这个新二叉树的根看成一个带权值的点，再次参与排序，选出来最小的两个
 * 4.重复直到所有数据全部遍历完成
 *
 * @Author jieHFUT
 * @Create 2025/7/30 4:00
 * @Version 1.0
 */
public class HuffmanTree {

    /**
     * 将这个数组构造成为一个霍夫曼树
     *
     * @param array 乱序的待转换为霍夫曼树的数组
     * @return 霍夫曼树的根节点
     */
    public Node huffmanTree(int[] array) {
        // 创建一个集合
        List<Node> nodes = new ArrayList<>();
        for(int data : array) {
            nodes.add(new Node(data));
        }
        // 将所有数据进行排序
        Collections.sort(nodes);

        // 在集合中还剩下两个的时候，直接全部拿出来
        while(nodes.size() > 1) {
            // 将所有数据进行排序（每次拿出来两个数据后，再插回去一个数据后也要排序）
            Collections.sort(nodes);
            //
            Node left = nodes.get(0);
            Node right = nodes.get(1);

            Node root = new Node(left.value + right.value);
            root.left = left;
            root.right = right;

            nodes.remove(0);
            nodes.remove(0);

            nodes.add(root);
        }
        // 集合列表中剩下最后的那一个就是要返回的根节点
        return nodes.get(0);
    }
}
