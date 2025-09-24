package leetcode;

/**
 * ClassName: Question101
 * Package: test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/9/17 14:22
 * @Version 1.0
 */
public class Question101 {

    public static void main(String[] args) {
        TreeNode root  = new TreeNode(2, new TreeNode(3, new TreeNode(4), new TreeNode(5, new TreeNode(8), new TreeNode(9))),
                                             new TreeNode(3, new TreeNode(5, new TreeNode(9), new TreeNode(8)), new TreeNode(4)));

        isSymmetric(root);
    }


    public static boolean isSymmetric(TreeNode root) {
        // 将一颗子树翻转过来
        if (root == null || (root.left == null && root.right == null)) return true;

        reverse(root.left);

        return isSameTree(root.left, root.right);
    }

    public static void reverse(TreeNode root) {

        if (root == null || (root.left == null && root.right == null)) return;

        if (root != null && root.left != null) {
            reverse(root.left);
        }

        if (root != null) {
            TreeNode swap = root.left;
            root.left = root.right;
            root.right = swap;
        }

        if (root != null && root.left != null) {
            reverse(root.left);
        }

    }

    public static boolean isSameTree(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) return true;
        if (node1 == null || node2 == null) return false;
        return node1.val == node2.val && isSameTree(node1.left, node2.left) && isSameTree(node1.right, node2.right);
    }
}
