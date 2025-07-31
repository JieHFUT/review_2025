package collection.m_avl;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ClassName: Node
 * Package: avl
 * Description:
 * 平衡二叉树 ()
 * @Author jieHFUT
 * @Create 2024/11/3 19:58
 * @Version 1.0
 */
public class Node implements Comparable<Node>{

    private int value;
    private Node left;
    private Node right;


    public Node(int value) { this.value = value; }
    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }
    public Node getLeft() { return left;}
    public void setLeft(Node left) { this.left = left; }
    public Node getRight() { return right; }
    public void setRight(Node right) { this.right = right; }


    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }

    @Override
    public String toString() {
        return "Node [value=" + value + "]";
    }


    public void preOrder(Node root) {
        System.out.println(root);
        if (root.left != null)
            preOrder(root.left);
        if (root.right != null)
            preOrder(root.right);
    }


    // 添加数据，同时将其进行排序
    // 如果当添加完一个节点后，不满足 AVL 树，就需要旋转（双旋转）
    public void add(Node toAdd) {

        if (value < toAdd.value) {
            // 如果树中的节点比要添加的节点元素小
            // 向树的右子树比较
            if(this.right == null)
                this.right = toAdd;
            else
                this.right.add(toAdd);
        } else {
            // 如果树中的节点比要添加的节点元素大
            // 向左子树比较
            if (this.left == null)
                this.left = toAdd;
            else
                this.left.add(toAdd);
        }

        // 需要左旋
        if (rightHeight() - leftHeight() > 1) {
            // 如果右子树的左子树 > 右子树的右子树 => 需要先对右子树进行右旋
            if (right != null && right.leftHeight() > right.rightHeight()) {
                right.rightRotate();
            }
            leftRotate();
            return;
        }
        // 需要右旋
        if (leftHeight() - rightHeight() > 1) {
            // 如果左子树的右子树 > 左子树的左子树 => 需要先对左子树进行左旋
            if (left != null && left.rightHeight() > left.leftHeight()) {
                left.leftRotate();
            }
            rightRotate();
        }

    }


    // 层序遍历
    public void levelOrder() {
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(this);
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.println(node);
            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
        }
    }


    /**
     * 返回当前节点的树的高度
     * @return
     */
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    /**
     * 返回当前节点的左子树的高度
     * @return
     */
    public int leftHeight() {
        if (left == null) return 0;
        return left.height();
    }

    /**
     * 返回当前节点右子树的高度
     * @return
     */
    public int rightHeight() {
        if (right == null) return 0;
        return right.height();
    }


    /**
     * 左旋转
     * 原因：右子树的高度 - 左子树高度 > 1
     */
    public void leftRotate() {
        Node newLeft = new Node(this.value);
        newLeft.left = left;
        newLeft.right = right.left;
        this.value = right.value;
        this.right = right.right;
        this.left = newLeft;
    }

    /**
     * 右旋转
     * 原因：左子树的高度 - 右子树高度 > 1
     */
    public void rightRotate() {
        Node newRight = new Node(this.value);
        newRight.right = right;
        newRight.left = left.right;
        this.value = left.value;
        this.left = left.left;
        this.right = newRight;
    }

}
