package collection.i_tree.tree1_binarytree;

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

    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node [no=" + no + ", name=" + name + "]";
    }




    // 从该节点进行前序遍历（根左右）
    public void preOrder() {
        // 先输出根节点
        System.out.println(this);
        if (this.left != null) {
            // 有左子树就遍历左子树
            this.left.preOrder();
        }
        if (this.right != null) {
            // 有右子树就遍历右子树
            this.right.preOrder();
        }
    }


    // 从该节点进行中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }


    // 从该节点进行后序遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }


    /**
     * 查找方法 -> 根据输入的 整数 来查找其节点
     *
     * 1.前序遍历来查找该节点
     * 2.中序遍历来查找该节点
     * 3.后序遍历来查找该节点
     */

    public Node preSearch(int toFind) {

        System.out.println("前序遍历来查找节点：");
        if (this.getNo() == toFind) {
            return this;
        }
        // 向左、右开始递归
        // 如果左边子树找到了就返回节点，没找到就不返回 => 去右边子树去查找
        Node isFound = null;
        if (this.left != null) {
            isFound = this.left.preSearch(toFind);
        }
        if (isFound != null) {
            // 说明左子树找到了
            return isFound;
        }

        if (this.right != null) {
            isFound =  this.right.preSearch(toFind);
        }
        if (isFound != null) {
            return isFound;
        }
        // 到这里说明根和左边和右边的子树都没有该节点
        return null;
    }

    public Node infixSearch(int toFind) {

        System.out.println("中序遍历来查找节点：");

        // 如果左边子树找到了就返回节点，没找到就不返回 => 去根去查找
        Node isFound = null;
        if (this.left != null) {
            isFound = this.left.preSearch(toFind);
        }
        if (isFound != null) {
            // 说明左子树找到了
            return isFound;
        }
        // 如果根找到了就返回节点，没找到就不返回 => 去右子树去查找
        if (this.getNo() == toFind) {
            return this;
        }
        if (this.right != null) {
            isFound =  this.right.preSearch(toFind);
        }
        if (isFound != null) {
            return isFound;
        }
        // 到这里说明左边和根和右边的子树都没有该节点
        return null;
    }

    public Node postSearch(int toFind) {

        System.out.println("后序遍历来查找节点：");

        // 如果左边子树找到了就返回节点，没找到就不返回 => 去右边子树去查找
        Node isFound = null;
        if (this.left != null) {
            isFound = this.left.preSearch(toFind);
        }
        if (isFound != null) {
            // 说明左子树找到了
            return isFound;
        }

        if (this.right != null) {
            isFound =  this.right.preSearch(toFind);
        }
        if (isFound != null) {
            return isFound;
        }
        if (this.getNo() == toFind) {
            return this;
        }
        // 到这里说明左边和右边的子树以及根节点都没有该节点
        return null;
    }


    /**
     * 根据字段 no 去删除二叉树的对应节点
     * 1.如果要删除的是叶子节点就直接删除
     * 2.如果要删除的不是叶子节点 => 直接删除该子树（考虑到目前二叉树没有什么规律，不用考虑往上提的原因，到后面的排序二叉树可以考虑提树）
     *       => 如果某一个节点的左子节点不为空并且左子节点就是要删除节点，删除其左子树，return
     *       => 如果某一个节点的右子节点不为空并且右子节点就是要删除节点，删除其右子树，return
     *       => 如果左子节点和右子节点都不是要删除的节点，递归
     *       => 如果树只有 root 节点，root 就是要删除的节点的话，就将这个树置空
     *       => 如果递归完，该二叉树没有要删除的节点，直接 return
     * @param no
     */
    public boolean deleteByNo(int no) {
        // 要考虑删除节点的时候不能直接判断当前节点是不是要删除的节点
        // 因为树是单向的，所以需要判断其子节点是不是要删除的节点
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return true;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return true;
        }

        // 左子节点和右子节点都不是要删除的节点，就开始向左右遍历
        if (this.left != null) this.left.deleteByNo(no);
        if (this.right != null) this.right.deleteByNo(no);

        return false;
    }

}
