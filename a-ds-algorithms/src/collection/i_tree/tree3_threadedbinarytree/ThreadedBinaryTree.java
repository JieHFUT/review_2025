package collection.i_tree.tree3_threadedbinarytree;

import lombok.Setter;

/**
 * ClassName: ThreadedBinaryTree
 * Package: collection.i_tree.tree3_threadedbinarytree
 * Description:
 * 线索化二叉树
 *     => private int no;
 *     => private String name;
 *     => private Node left;
 *     => private Node right;
 *     => private int leftType;  // 0 意味着指向左子树，1意味着指向前驱节点
 *     => private int rightType; // 0 意味着指向右子树，1意味着指向后继节点
 *
 * @Author jieHFUT
 * @Create 2025/7/29 22:28
 * @Version 1.0
 */
@Setter
public class ThreadedBinaryTree {

    private Node root; // 根节点

    private Node prev; // 用来记录 node 的前驱节点的值


    /**
     * 编写对一个二叉树进行中序线索化的方法
     * @param node
     */
    public void infixThreadedBinaryTree(Node node) {
        

    }

    /**
     * 遍历线索化二叉树
     */
    public void threadedBinaryTree() {

    }




}
