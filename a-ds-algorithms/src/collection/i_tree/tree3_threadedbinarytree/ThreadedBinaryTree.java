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
     * 编写对一个二叉树进行中序线索化的方法（左根右）
     * 1、线索化左子树
     * 2、线索化根节点
     * 3、线索化右节点
     *
     * @param node
     */
    public void infixThreadedBinaryTree(Node node) {
        if (node == null) return;
        // 1、线索化左子树
        infixThreadedBinaryTree(node.getLeft());
        // 2、线索化根节点

        // 先处理当前节点的前驱节点
        if (node.getLeft() == null) {
            node.setLeft(prev);
            node.setLeftType(1);
        }
        // 下一次挪动的时候设置后继
        if (prev != null && prev.getRight() == null) {
            // 前驱节点的右指针指向当前节点
            prev.setRight(node);
            node.setRightType(1);
        }
        // 很重要，每处理一个节点以后，当前节点就是下一个节点的前驱节点
        prev = node;

        // 3、线索化右节点
        infixThreadedBinaryTree(node.getRight());
    }

    /**
     * 中序遍历线索化二叉树
     */
    public void threadedBinaryTree() {
        Node node = root;
        if (node == null) return;
        // 左 根 右
        while (node != null) {
            // 一直到左边，最左边
            if (node.getLeftType() == 0)
                node = node.getLeft();
            // 直接输出最左边
            System.out.println(node);

            if (node.getRightType() == 1){
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }




}
