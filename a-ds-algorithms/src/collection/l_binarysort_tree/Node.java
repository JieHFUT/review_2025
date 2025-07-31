package collection.l_binarysort_tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ClassName: Node
 * Package: binarysorttree
 * Description:
 * 二叉排序树
 * 一个非叶子节点的左子节点总是比该节点小，右子节点总是比该节点大
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


    /**
     * 前序遍历该二叉排序树
     * @param node
     */
    public void preOrder(Node node) {
        System.out.println(node);
        if (node.left != null)
            preOrder(node.left);
        if (node.right != null)
            preOrder(node.right);
    }


    /**
     * 向一个二叉排序树中添加一个节点，添加完成后需要保证仍然是一个二叉排序树
     * 根节点 root && node 节点都不为 null
     * @param node 要被添加的节点
     */
    public void add(Node node) {

        if (this.compareTo(node) < 0) {
            // 向右子树看
            if (this.right == null)
                this.right = node;
            else
                this.right.add(node);
        } else {
            // 向左子节点查看
            if (this.left == null)
                this.left = node;
            else
                this.left.add(node);
        }
    }


    /**
     * 对该二叉排序树进行层序遍历
     */
    public void levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(this);
        while (!queue.isEmpty()) {
            Node peek = queue.peek();
            if (peek.left != null)
                queue.add(peek.left);
            if (peek.right != null)
                queue.add(peek.right);
            System.out.println(peek);
            queue.poll();
        }
    }


    /**
     * 查找节点
     * 二叉排序树
     * @param value
     * @return
     */
    public Node search(int value) {
        // 1.如果当前节点就是要查找的节点
        if (this.value == value)
            return this;
        // 2.如果当前节点的数值比 value 大
        if (this.value > value && this.left != null) {
            // 向左边找
            return this.left.search(value);
        }
        if (this.value < value && this.right != null) {
            return this.right.search(value);
        }
        return null;
    }


    /**
     * 寻找该节点的父亲节点
     * 没有父亲节点或者没有找到该节点就返回 null
     * @param value
     * @return
     */
    public Node searchParent(int value) {
        // 思路：采用双指针法
        Node prev = null;
        Node root = this;
        boolean found = false;

        while (true) {
            if (root.value == value) {
                // 找到了该节点
                found = true;
                break;
            }
            if (root.value > value && root.left != null) {
                prev = root;
                root = root.left;
            } else if (root.value < value && root.right != null) {
                prev = root;
                root = root.right;
            } else {
                // 整个排序二叉树就没有该节点
                break;
            }

        }
        // 在这个位置有两种情况
        // 1.匹配到该节点，返回 prev 就是其父亲节点
        // 2.整个排序二叉树中没有该节点
        if (found) {
            return prev;
        } else {
            return null;
        }
    }

    /**
     * 通过递归的方法寻找其父亲节点
     * @param value
     * @return
     */
    public Node searchParent2(int value) {

        if (this.value == value) return null;

        if ((this.left != null && this.left.value == value)
                || (this.right != null && this.right.value == value)) {
            // 决定了递归时候 if (this.value == value) return null; 不会生效
            return this;
        } else {
            if (this.left != null && this.left.value > value) {
                // 向左寻找
                return this.left.searchParent2(value);
            } else if (this.right != null && this.right.value < value) {
                return this.right.searchParent2(value);
            } else {
                return null;
            }
        }

    }







}
