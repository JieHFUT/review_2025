package test;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: Question111
 * Package: test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/9/18 1:45
 * @Version 1.0
 */
public class Question111 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,
                null, new TreeNode(2,
                null, new TreeNode(3,
                null, new TreeNode(4,
                null, new TreeNode(5)))));

        System.out.println(minDepth(root));
    }


    public static int minDepth(TreeNode root) {
        // 最小深度
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;

        int depth = 0;
        try {
            List<TreeNode> rest = new ArrayList<>();
            rest.add(root);

            while (rest.size() > 0) {
                depth++;
                rest = getEveryLevel(rest);
            }
        } catch (Exception e) {
            return depth;
        } finally {
            return depth;
        }

    }


    public static List<TreeNode> getEveryLevel(List<TreeNode> rest) throws Exception {

        List<TreeNode> ans = new ArrayList<>();

        if (rest == null) return ans;

        for (TreeNode node : rest) {
            if (node != null && node.left == null && node.right == null) {
                throw new Exception("发现叶子节点");
            }

            if (node != null && node.left != null) ans.add(node.left);
            if (node != null && node.right != null) ans.add(node.right);
        }

        return ans;
    }
}
