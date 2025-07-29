package collection.i_tree.tree2_sequencetree;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: SequenceTree
 * Package: collection.i_tree.tree2_sequencetree
 * Description:
 *
 * Node 数组存储为 二叉树
 * 1.前序顺序存储二叉树
 * 2.中序
 * 3.后序
 *
 * @Author jieHFUT
 * @Create 2025/7/29 15:57
 * @Version 1.0
 */
public class SequenceTree {


    /**
     *
     * @param array 一个节点数组，将数组以前序遍历的方式，将遍历的结果输出到集合 ArrayList 中
     * array[0, 1, 2, 3, 4, 5, 6, 7, 8, 9， 10， 11， 12， 13， 14]
     *               0
     *         1           2
     *      3      4     5     6
     *    7   8  9  10 11 12 13 14
     * 一个数组转化为二叉树的下标索引如上所示：以下标为准，将数组的前序遍历结果输出到集合中
     *
     * @return list[0,1,3,7,8,4,9,10,2,5,11,12,6,13,14]
     * n -> 2n+1
     * n -> 2n+2
     * n -> (n-1)/2
     */
    public static List<Node> preOrderToList(Node[] array) {
        return preOrderToList(array, 0);
    }
    // index 就是数组的下标
    public static List<Node> preOrderToList(Node[] array, int index) {
        // 前序顺序存储二叉树：根左右
        List<Node> list = new ArrayList<>();

        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("array is null or empty");
        }
        // 根
        list.add(array[index]);
        // 左
        if (index * 2 + 1 < array.length) {
            list.addAll(preOrderToList(array, index * 2 + 1));
        }
        // 右
        if (index * 2 + 2 < array.length) {
            list.addAll(preOrderToList(array, index * 2 + 2));
        }

        return list; // 该节点的全部前序遍历
    }


}
