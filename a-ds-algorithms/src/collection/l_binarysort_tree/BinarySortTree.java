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

    /**
     * 向排序树中添加元素
     * @param toAdd
     */
    public void add(Node toAdd) {
        if (toAdd == null) return;
        if (getRoot() == null) {
            root = toAdd;
            return;
        }
        root.add(toAdd);
    }


    /**
     * 输出排序树的前序遍历
     */
    public void preOrder() {
        if (root == null) return;
        this.getRoot().preOrder(root);
    }

    /**
     * 层序遍历
     */
    public void levelOrder() {
        if (root == null) return;
        this.getRoot().levelOrder();
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
     * 查找该节点的父节点
     * @param key
     * @return
     */
    public Node searchParent(int key) {
        if (root == null) return null;
        return root.searchParent(key);
    }

    public boolean delete(int key) {
        if (root == null) throw new RuntimeException("Tree is empty");
        Node target = search(key);
        if (target == null) {
            System.out.println("don't have target");
            return false;
        }
        if (root.getLeft() == null && root.getRight() == null) {
            // 只有一个根节点
            root = null;
            return true;
        }

        Node parent = searchParent(key);
        // target : 要删除的节点
        // parent : 要删除节点的父节点
        if (target.getLeft() == null && target.getRight() == null) {
            // 要删除的是叶子节点
            if (parent.getLeft() != null && parent.getLeft().getValue() == key)
                parent.setLeft(null);
            else
                parent.setRight(null);
            return true;
        } else if (target.getLeft() == null) {
            // 要删除的节点只有右子树
            if (parent.getLeft() == target)
                parent.setLeft(target.getRight());
            else
                parent.setRight(target.getRight());
        } else if (target.getRight() == null){
            // 要删除的节点只有左子树
            if (parent.getLeft() == target)
                parent.setLeft(target.getLeft());
            else
                parent.setRight(target.getLeft());
        } else {
            // 要删除的节点左右子树都在
            int newValue = delRightTreeMin(target.getRight());
            target.setValue(newValue);
        }
        return true;
    }

    /**
     * 找到以该点为根节点的树的最小节点，删除这个节点并且返回其值
     * @param node
     * @return
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        while (target.getLeft() != null) {
            target = target.getLeft();
        }
        int ret = target.getValue();
        delete(ret);
        return ret;
    }
}
