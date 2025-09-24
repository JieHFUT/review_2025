package leetcode;

/**
 * ClassName: Question114
 * Package: test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/9/18 2:54
 * @Version 1.0
 */
public class Question114 {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1,
                new TreeNode(2, new TreeNode(3), new TreeNode(4)),
                new TreeNode(5, null, new TreeNode(6)));
        flatten(new TreeNode(0));
        System.out.println();
    }

    public static void flatten(TreeNode node) {
        // 先序遍历 - 根左右
        TreeNode ret = root;
        preorder(node);
        node.left = null;
        node.right = ret.right.right;
    }

    static TreeNode root = new TreeNode();

    public static void preorder(TreeNode node) {
        //
        if (node != null) {
            root.right = new TreeNode(node.val);
            root = root.right;
        }

        if (node != null && node.left != null) {
            preorder(node.left);
        }

        if (node != null && node.right != null) {
            preorder(node.right);
        }
    }
}
