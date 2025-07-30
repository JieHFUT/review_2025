package collection.l_binarysort_tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ClassName: Node
 * Package: binarysorttree
 * Description:
 * 二叉排序树
 * @Author jieHFUT
 * @Create 2024/11/1 22:09
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
    public void add(Node toAdd) {
        if (this.compareTo(toAdd) < 0) {
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
     * 根据值来找到节点，如果没有找到就返回 null
     * @param value
     * @return
     */
    public Node search(int value) {
        return search(this, value);
    }
    public Node search(Node node, int value) {
        // 1. 如果要找的值就在该节点上
        if (node.value == value) return node;
        // 2. 如果要找的值比该节点的值大就向右递归，否则就像左递归
        if (node.value > value) {
            if (node.left != null)
                return search(node.left, value);
            else
                return null;
        } else {
            if (node.right != null)
                return search(node.right, value);
            else
                return null;
        }
    }

    /**
     * 查找该节点的父亲节点，没有父节点或者没有找到该节点就返回 null
     * @param value
     * @return
     */
    public Node searchParent(int value) {
        if (this.value == value) return null;

        if (this.left != null && this.left.value == value) {
            // 该节点的左子节点就是 target
            return this;
        } else if (this.right != null && this.right.value == value) {
            // 该节点的右子节点就是 target
            return this;
        } else {
            // 该节点的子节点不是要找的节点，往下寻找
            if (this.value > value && this.left != null) {
                return this.left.searchParent(value);
            } else if (this.value <= value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }
    }



}
