package collection.i_tree.tree1_binarytree;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

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
    // 9.判断是否是高度平衡的二叉树（一个节点的两个子树的高度差不大于1，并且其子树也是高度平衡的二叉树）
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
        if (k <= 0) throw new RuntimeException("输入层数非法！");
        if (root == null) return 0;

        // 可以想到，在 root 时候去找第 K 层的元素
        //         在 root.子节点时候去找第 K-1 层的元素
        //         ..............................
        //         在 某一层的时候就要去找第 1 层元素
        // 创建一个 List 集合用于存储某一层的元素，当 K 减到1的时候遍历 List 找到其所有子节点即可
        List<Node> nodes = new ArrayList<>();
        nodes.add(root);

        while (k > 1) {
            // 遍历 nodes 集合，将其所有的非空子节点放到新的集合中，再将新的集合赋值给 nodes
            Iterator<Node> iterator = nodes.iterator();
            List<Node> mid = new ArrayList<>();
            while (iterator.hasNext()) {
                Node node = iterator.next();
                if (node.getLeft() != null)
                    mid.add(node.getLeft());
                if (node.getRight() != null)
                    mid.add(node.getRight());
            }
            nodes = mid;
        }
        // 此时集合的长度就是第K层的长度
        return nodes.size();
    }
    // 第二种办法，使用递归处理（ROOT的第K层 -> 其子节点的第 K-1 层）
    //                                    最后到叶子节点的第1层
    public int numberOfNodesOfKLevel2(int k) {
        return numberOfNodesOfKLevel3(root, k);
    }
    public int numberOfNodesOfKLevel3(Node node, int k) {
        if (node == null) return 0;
        if (k == 1 && node != null) return 1;
        return numberOfNodesOfKLevel3(node.getLeft(), k-1) + numberOfNodesOfKLevel3(node.getRight(), k-1);
    }

    // 3.获取二叉树的高度
    public int height() {
        // 返回 1 + max(左子树的高度，右子树的高度)
        return height(root);
    }
    public int height(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }


    // 4.检测编号 no 的元素是否存在，如果存在返回该节点，否则返回 null
    public Node findByNo(int no) {
        return findByNo(root, no);
    }
    public Node findByNo(Node node, int no) {
        // 前序遍历进行寻找
        if (node == null) return null;
        if (node.getNo() == no) return node;
        // 左子树进行寻找
        Node isFound = null;
        isFound = findByNo(node.getLeft(), no);
        if (isFound != null) return isFound;
        // 左子树没有找到

        isFound = findByNo(node.getRight(), no);
        if (isFound != null) return isFound;

        // 根，左，右 全部没找到
        return null;
    }



    // 5.对二叉树进行层序遍历（队列   非递归）
    public void levelOrder() {
        // 第一种做法，通过队列来实现
        if (root == null) return;
        Queue<Node> queue = new LinkedList<>();

        Node node = root;
        queue.add(node);
        while (!queue.isEmpty()) {
            // 先进先出
            if (queue.peek().getLeft() != null) {
                queue.add(queue.peek().getLeft());
            }
            if (queue.peek().getRight() != null) {
                queue.add(queue.peek().getRight());
            }
            System.out.print(queue.poll() + "\t");
        }
    }

    // 使用集合来存储层序遍历的结果
    public List<List<Node>> levelOrder2() {
        if (root == null) return null;
        Node node = root;

        List<List<Node>> result = new ArrayList<>();
        // 先在外面添加第一层
        List<Node> everyLevel = new ArrayList<>();
        everyLevel.add(node);

        result.add(everyLevel);
        while (!everyLevel.isEmpty()) {
            // 遍历某一层 everyLevel，将其下一层的所有非空节点添加到一个新集合中，再将其覆盖到 everyLevel 中
            Iterator<Node> iterator = everyLevel.iterator();
            List<Node> mid = new ArrayList<>();
            while (iterator.hasNext()) {
                Node node1 = iterator.next();
                if (node1.getLeft() != null)
                    mid.add(node1.getLeft());
                if (node1.getRight() != null)
                    mid.add(node1.getRight());
            }
            everyLevel = mid;
        }
        result.add(everyLevel);
        return result;
    }



    // 6.判读一棵树是不是完全二叉树（完全二叉树是叶子节点只能出现在最底层，并且均在左侧）
    // 思路，向队列中一直放节点，子树是空的就放 null
    // 直到第一个父亲节点是 null 停止

    public boolean isCompleteTree() {
        Node node = root;
        if (node == null) return true;

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (true) {
            Node out = queue.poll();

            if (out != null) {
                queue.add(out.getLeft());
                queue.add(out.getRight());
            } else {
                // 在这里第一次遇到了从队列中取出来的元素是 null
                // node node node node node node node node node node  null node node null node node ...
                // node node node node node node node node node node  null null null null null null ...
                break;
            }
        }
        // 如果队列中剩下的全是 null 就说明是完全二叉树
        while (!queue.isEmpty()) {
            Node out = queue.poll();
            if (out != null) {
                return false;
            }
        }
        return true;
    }


    // 7.判断两颗树是否相同
    public boolean isSameTree(Node other) {
        /**
         * 思路：
         * 1.先判断根节点是否相同
         *      根节点不相同：return false
         *      根节点相同，再比较子树、
         *         如果左子树和右子树有一个不相同 return false
         */
        return isSameTree(root, other);

    }
    public boolean isSameTree(Node self, Node other) {
        if (self == null && other == null) return true;
        if (self == null || other == null) return false;
        // 到此两个根节点都不是 null
        if (self.getNo() != other.getNo()) return false;
        // 父亲节点已经一样，比较子节点
        return isSameTree(self.getLeft(), other.getLeft())
                && isSameTree(self.getRight(), other.getRight());
    }


    // 8.判断一棵树是否包含另外一棵树
    public boolean isSubTree(Node other) {
        return isSubTree(root, other);
    }
    public boolean isSubTree(Node self, Node other) {
        if (self == null || other == null) return true;
        // 两颗树都不是 null
        if (isSameTree(self, other)) return true;
        return isSameTree(self.getLeft(), other) || isSameTree(self.getRight(), other);
    }


    // 9.判断是否是高度平衡的二叉树（ height()获取树的高度 ）
    // 一个节点的两个子树的高度差不大于1，并且其子树也是高度平衡的二叉树
    public boolean isBalanced() {
        return isBalanced(root);
    }
    public boolean isBalanced(Node root) {
        if (root == null) return false;
        if (Math.abs(height(root.getLeft()) - height(root.getRight())) > 1) return false;

        return isBalanced(root.getLeft()) && isBalanced(root.getRight());
    }


    // 10.判断一颗二叉树是否轴对称
    public boolean isSymmetricTree() {
        /**
         * 思路：
         * 方法一、将其左子树的每一个节点的左右反过来，如果反过来后和右子树一样 return true
         * 方法二：
         */
        return isSymmetricTree(root);
    }
    public boolean isSymmetricTree(Node node) {
        if (node == null) return false;
        if (node.getLeft() == null && node.getRight() == null) return true;
        if (node.getLeft() == null || node.getRight() == null) return false;
        turnOneTree(node.getLeft());
        if (isSameTree(node.getRight(), node.getLeft())) return true;
        return false;
    }

    public boolean isSymmetricTree(Node left, Node right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        if (left.getNo() != right.getNo()) return false;
        return isSymmetricTree(left.getLeft(), right.getRight())
                && isSymmetricTree(left.getRight(), right.getLeft());
    }



    // 11.将一棵树的每一个节点的左右子树指向反过来
    public void turnOneTree() {
        turnOneTree(root);
    }
    public void turnOneTree(Node node) {
        if (node == null) return;
        swap(node);
        turnOneTree(node.getLeft());
        turnOneTree(node.getRight());
    }
    public void swap(Node toTurn) {
        Node temp = toTurn.getLeft();
        toTurn.setLeft(toTurn.getRight());
        toTurn.setRight(temp);
    }


    // 12.找到两个指定节点的最近的公共祖先
    public Node lowestCommonAncestor(Node root, Node p, Node q) {
        /**
         * 思路：
         * 方法一：使用集合 list 记录两个节点的路径，比较路径，寻找相同的最早祖先
         * 方法二：
         */
        List<Node> patha = new ArrayList<>();
        List<Node> pathb = new ArrayList<>();
        patha = findWay(root, p);
        pathb = findWay(root, q);

        Iterator<Node> iterator = patha.iterator();
        while (iterator.hasNext()) {
            Node node1 = iterator.next();
            for(Node node2 : pathb) {
                if (node1.getNo() == node2.getNo()) return node1;
            }
        }
        return null;
    }

    /**
     *
     * @param root
     * @param p
     * @return 从节点 p 到根节点的一个通路
     */
    private List<Node> findWay(Node root, Node p) {
        List<Node> path = new ArrayList<>();
        // 判断目标节点是否通过该节点
        if (root.getNo() == p.getNo()) {
            path.add(root);
            return path;
        }
        List<Node> pathlr = null;
        pathlr = findWay(root.getLeft(), p);
        if (pathlr != null) {
            path.addAll(pathlr);
        }
        pathlr = findWay(root.getRight(), p);
        if (pathlr != null) {
            path.addAll(pathlr);
        }
        return null;
    }


}
