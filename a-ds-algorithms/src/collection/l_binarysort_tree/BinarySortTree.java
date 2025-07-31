package collection.l_binarysort_tree;

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
        if (root == null) root = node;
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
     * @param key
     * @return
     */
    public boolean delete(int key) {
        
    }




}
