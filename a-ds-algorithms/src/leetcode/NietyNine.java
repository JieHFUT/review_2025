package leetcode;

/**
 * ClassName: NietyNine
 * Package: test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/9/17 2:45
 * @Version 1.0
 */
public class NietyNine {

    public static void main(String[] args) {
        NietyNine nietyNine = new NietyNine();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);

        node1.left = node3;
        node3.right = node2;
        nietyNine.recoverTree(node1);

    }

    public void recoverTree(TreeNode root) {
        // 思路：第一遍遍历找到变小的数字，然后找到第一个大于他的数字进行交换
        mid(root);
        swap(root);
        swap2(root);
    }


    int min = Integer.MIN_VALUE;

    public void mid(TreeNode root) {
        if (root != null && root.left != null) {
            mid(root.left);
        }

        if (root != null && root.val >= min) {
            min = root.val;
        }

        if (root != null && root.val < min) {
            min = root.val;
            return;
        }

        if (root != null && root.right != null) {
            mid(root.right);
        }
    }

    int swap = Integer.MIN_VALUE;

    public void swap(TreeNode root) {
        if (root != null && root.left != null) {
            swap(root.left);
        }

        // 找到第一个大于 min 的数字和 min 进行交换
        if (root != null && root.val > min) {
            swap = root.val;
            root.val = min;
            return;
        }

        if (root != null && root.right != null) {
            swap(root.right);
        }
    }

    public void swap2(TreeNode root) {
        if (root != null && root.left != null) {
            swap2(root.left);
        }

        // 找到第一个大于 min 的数字和 min 进行交换
        if (root != null && root.val == min) {
            root.val = swap;
            return;
        }

        if (root != null && root.right != null) {
            swap2(root.right);
        }
    }
}
