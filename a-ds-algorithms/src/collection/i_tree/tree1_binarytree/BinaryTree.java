package collection.i_tree.tree1_binarytree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ClassName: BinaryTree
 * Package: collection.i_tree.tree1_binarytree
 * Description:
 * 二叉树
 * @Author jieHFUT
 * @Create 2025/7/29 0:51
 * @Version 1.0
 */

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BinaryTree {

    // 设置根节点
    private Node root;

    // 对二叉树进行前序遍历
    public void preOrder() {
        if (root != null) {
            root.preOrder();
        } else {
            throw new RuntimeException("该二叉树是空树，无法进行前序遍历！");
        }
    }

    // 对二叉树进行中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            throw new RuntimeException("该二叉树是空树，无法进行中序遍历！");
        }
    }

    // 对二叉树进行后序遍历
    public void postOrder() {
        if (root != null) {
            root.postOrder();
        } else {
            throw new RuntimeException("该二叉树是空树，无法进行后序遍历！");
        }
    }

    // 根据前序遍历搜索某一个节点（no）
    public Node preSearch(int toFind) {
        if (root != null) {
            return root.preSearch(toFind);
        } else {
            throw new RuntimeException("该二叉树是空树，无法进行前序搜索！");
        }
    }

    // 根据中序遍历搜索某一个节点（no）
    public Node infixSearch(int toFind) {
        if (root != null) {
            return root.infixSearch(toFind);
        } else {
            throw new RuntimeException("该二叉树是空树，无法进行中序搜索！");
        }
    }

    // 根据后序遍历搜索某一个节点（no）
    public Node postSearch(int toFind) {
        if (root != null) {
            return root.postSearch(toFind);
        } else {
            throw new RuntimeException("该二叉树是空树，无法进行后序搜索！");
        }
    }


    // 根据字段 no 去删除二叉树的对应节点
    public boolean deleteByNode(int toDelete) {
        if (root == null) throw new RuntimeException("该二叉树是空树，无法进行删除！");
        if (root.getNo() == toDelete) {
            this.root = null;
            return true;
        }
        return root.deleteByNo(toDelete);
    }




    //////////////////////////////////////////////////////////////
    /// 下面是针对树结构的相关算法题目 root
    //////////////////////////////////////////////////////////////
    // 1.获取树中叶子节点的个数
    // 2.获取第 K 层节点的个数
    // 3.获取二叉树的高度
    // 4.检测编号 no 的元素是否存在
    // 5.对二叉树进行层序遍历（队列   非递归）
    // 6.判读一棵树是不是完全二叉树（完全二叉树是叶子节点只能出现在最底层，并且均在左侧）
    // 7.判断两颗树是否相同
    // 8.判断一棵树是否包含另外一棵树
    // 9.判断是否是高度平衡的二叉树
    // 10.判断一颗二叉树是否轴对称
    // 11.将一棵树的每一个节点的左右子树指向反过来
    // 12.找到两个指定节点的最近的公共祖先

    // 获取树中节点的个数
    public int numberOfNodes() {
        return numberOfNodes(root);
    }
    public int numberOfNodes(Node root) {
        if (root == null) return 0;
        return 1 + numberOfNodes(root.getLeft()) + numberOfNodes(root.getRight());
    }

    // 1.获取树中叶子节点的个数
    public int numberOfLeaves() {
        return numberOfLeaves(root);
        // return numberOfLeaves(root);
    }
    public int numberOfLeaves(Node root) {
        if (root == null) return 0;
        if (root.getLeft() == null && root.getRight() == null) return 1;
        if (root.getLeft() == null) return numberOfLeaves(root.getRight());
        if (root.getRight() == null) return numberOfLeaves(root.getLeft());
        // 左右子树都不为空
        return numberOfLeaves(root.getLeft()) + numberOfLeaves(root.getRight());
    }

    int recodeLeaves = 0;
    public int numberOfLeaves2(Node root) {
        if (root == null) return 0;
        if (root.getLeft() == null && root.getRight() == null) recodeLeaves++;
        if (root.getLeft() == null) numberOfLeaves(root.getRight());
        if (root.getRight() == null) numberOfLeaves(root.getLeft());
        return recodeLeaves;
    }



    // 2.获取第 K 层节点的个数
    public int numberOfNodesOfKLevel(int k) {
        
    }




}
