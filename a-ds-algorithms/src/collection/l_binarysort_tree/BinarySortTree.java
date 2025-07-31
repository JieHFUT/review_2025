package collection.l_binarysort_tree;

import java.util.NoSuchElementException;

/**
 * ClassName: BinarySortTree
 * Package: binarysorttree
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/11/2 20:11
 * @Version 1.0
 */
public class BinarySortTree {

    private Node root;
    public BinarySortTree() {}
    public Node getRoot() {return root;}


    // 前序遍历该节点（根-左-右）
    public void preOrder() {
        if (root == null) return;
        root.preOrder(root);
    }

    // 层序遍历
    public void levelOrder() {
        if (root == null) return;
        root.levelOrder();
    }

    /**
     * 向一颗排序树中添加一个节点
     * @param node
     */
    public void add(Node node) {
        if (node == null) return;
        if (root == null) {
            root = node;
            return;
        }
        root.add(node);
    }

    /**
     * 查找节点
     * @param key
     * @return
     */
    public Node search(int key) {
        if (root == null) return null;
        return root.search(key);
    }


    /**
     * 返回该节点的父亲节点
     * @param key
     * @return
     */
    public Node searchParent(int key) {
        if (root == null) return null;
        return root.searchParent(key);
    }


    /**
     * 删除某一个节点，删除完成后还需要保证是排序二叉树
     * 思路：
     *    1.如果要删除的是叶子节点：直接删除该节点
     *    2.如果要删除的节点有一颗子树：子树直接上提
     *    3.如果要删除的节点有两颗子树：
     * @param key
     * @return
     */
    public boolean delete(int key) {
        if (root == null) throw new RuntimeException("Tree is empty");
        Node toDelete = search(key);

        // 没有找到该节点
        if (toDelete == null) return false;

        // 只有根节点
        if (root.getLeft() == null && root.getRight() == null) {
            root = null;
            return true;
        }

        // 下面的情况就是该树中有该节点
        Node parent = searchParent(key);
        // toDelete：要删除的节点
        // parent：要删除节点的父亲节点
        if (toDelete.getLeft() == null && toDelete.getRight() == null) {
            // 要删除的是叶子节点
            if (parent.getLeft() != null && parent.getValue() == toDelete.getValue())
                parent.setLeft(null);
            else
                parent.setRight(null);
        } else if (toDelete.getLeft() == null) {
            // 要删除的节点只有一个右子树（直接将其子树缀到其父亲节点上）
            if (parent.getLeft().getValue() == toDelete.getValue())
                parent.setLeft(toDelete.getRight());
            else
                parent.setRight(toDelete.getRight());
        } else if (toDelete.getRight() == null) {
            // 要删除的节点只有一个左子树（直接将其子树缀到其父亲节点上）
            if (parent.getLeft().getValue() == toDelete.getValue())
                parent.setLeft(toDelete.getLeft());
            else
                parent.setRight(toDelete.getLeft());
        } else {
            // 要删除的节点的左右子树都在（如以其右子节点为根，和其左子节点再造一个二叉排序树）
            // 其父亲节点的子节点就是新的根
            int newValue = delRightTreeMin(toDelete.getRight());

        }





        return false;
    }

    private int delRightTreeMin(Node right) {

    }


}
