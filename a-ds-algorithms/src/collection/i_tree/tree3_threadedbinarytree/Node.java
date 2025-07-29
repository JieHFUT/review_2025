package collection.i_tree.tree3_threadedbinarytree;

import lombok.Getter;
import lombok.Setter;

/**
 * ClassName: Node
 * Package: collection.i_tree
 * Description:
 *
 * 树的节点类
 * 1.前中后序遍历
 * 2.前中后序查找
 *
 * 3.实现顺序化存储二叉树的功能
 * array[1,2,3,4,5,6,7,8,9,10....]
 * 二叉树的节点存储在数组中，要求不影响二叉树的结构
 * 题目：要求实现将一个数组中的 Node 节点以二叉树前中后序的方法遍历一遍
 * 在二叉树中
 *      1.第 N 个节点的左子节点的下标是 2*N+1
 *      2.第 N 个节点的右子节点的下标是 2*N+2
 *      3.第 N 个节点的父亲节点的下标是 (N-1)/2
 *
 *
 * 4.这把包里面是线索化二叉树的做法
 *   n个结点的二叉链表中含有n+1  【公式 2n-(n-1)=n+1】 个空指针域
 *   根据线索性质的不同，线索二叉树可分为前序线索二叉树、中序线索二叉树和后序线索二叉树三种
 *
 *
 * @Author jieHFUT
 * @Create 2025/7/29 0:40
 * @Version 1.0
 */

@Setter
@Getter
public class Node {

    private int no;
    private String name;
    private Node left;
    private Node right;
    private int leftType;  // 0 意味着指向左子树，1意味着指向前驱节点
    private int rightType; // 0 意味着指向右子树，1意味着指向后继节点

    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node [no=" + no + ", name=" + name + "]";
    }


}
