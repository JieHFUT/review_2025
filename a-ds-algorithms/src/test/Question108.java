package test;

/**
 * ClassName: Question108
 * Package: test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/9/17 16:23
 * @Version 1.0
 */
public class Question108 {

    public static void main(String[] args) {
        sortedArrayToBST(new int[]{-10,-3,0,5,9});

    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        // 有序数组 => 平衡二叉搜索树

        int mid = (nums.length - 1) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        if (mid > 0) {
            int[] left = new int[mid];
            for (int i = 0; i < mid; i++) {
                left[i] = nums[i];
            }

            root.left = sortedArrayToBST(left);
        }

        if (mid < nums.length - 1) {
            int[] right = new int[nums.length - 1 - mid];
            for (int i = 0; i < right.length; i++) {
                right[i] = nums[mid + i + 1];
            }

            root.right = sortedArrayToBST(right);
        }

        return root;
    }
}
