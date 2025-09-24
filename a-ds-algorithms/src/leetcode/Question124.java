package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: Question124
 * Package: test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/9/21 23:22
 * @Version 1.0
 */
public class Question124 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println(maxPathSum(root));
    }


    public static int maxPathSum(TreeNode root) {
        // 最大路径合：包不包含根节点

        // 必须包含根节点
        int contain = root.val;
        // 是否包含根节点的左右节点
        if (root.left != null) {
            HashMap<TreeNode, Integer> mapleft = new HashMap<>();
            mapleft.put(root.left, root.left.val);
            int left = maxPathSumContain(mapleft);
            contain = Math.max(contain, left + contain);
        }

        if (root.right != null) {
            HashMap<TreeNode, Integer> mapright = new HashMap<>();
            mapright.put(root.right, root.right.val);
            int right = maxPathSumContain(mapright);
            contain = Math.max(contain, right + contain);
        }



        // 不包含根节点
        if (root != null && root.left != null && root.right != null) {
            return Math.max(contain, Math.max(maxPathSum(root.left), maxPathSum(root.right)));
        } else if (root != null && root.left != null) {
            return Math.max(contain, maxPathSum(root.left));
        } else if (root != null && root.right != null) {
            return Math.max(contain, maxPathSum(root.right));
        } else {
            return root.val;
        }
    }



    public static int maxPathSumContain(HashMap<TreeNode, Integer> map) {

        int ret = Integer.MIN_VALUE;
        while (map.size() > 0) {

            HashMap<TreeNode, Integer> toFound = new HashMap<>();
            for (Map.Entry<TreeNode, Integer> entry : map.entrySet()) {
                TreeNode node = entry.getKey();
                int value = entry.getValue();

                if(value > ret) ret = value;

                if (node.left != null) {
                    toFound.put(node.left, value + node.left.val);
                }

                if (node.right != null) {
                    toFound.put(node.right, value + node.right.val);
                }
            }
            map = toFound;
        }

        return ret;
    }
}
