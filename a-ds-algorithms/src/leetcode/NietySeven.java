package leetcode;

/**
 * ClassName: NietySeven
 * Package: test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/9/16 22:13
 * @Version 1.0
 */
public class NietySeven {

    public static void main(String[] args) {
        System.out.println(isInterleave("bbbbbabbbbabaababaaaabbababbaaabbabbaaabaaaaababbbababbbbbabbbbababbabaabababbbaabababababbbaaababaa",
                "babaaaabbababbbabbbbaabaabbaabbbbaabaaabaababaaaabaaabbaaabaaaabaabaabbbbbbbbbbbabaaabbababbabbabaabv",
                "babbbabbbaaabbababbbbababaabbabaabaaabbbbabbbaaabbbaaaaabbbbaabbaaabababbaaaaaabababbababaababbababbbababbbbaaaabaabbabbaaaaabbabbaaaabbbaabaaabaababaababbaaabbbbbabbbbaabbabaabbbbabaaabbababbabbabbab"));

    }

    public static boolean isInterleave(String s1, String s2, String s3) {
        // 思路：
        return isInterleaveWithOrder(true, s1, s2, s3) || isInterleaveWithOrder(false, s1, s2, s3);
    }


    public static boolean isInterleaveWithOrder(boolean whitch, String s1, String s2, String s3) {

        if (s1.length() == 0 && s2.length() == 0 && s3.length() == 0) return true;

        if (whitch) {
            // 先 s1
            int count = 0;
            while ((count < s1.length() && count < s3.length()) && (s1.charAt(count) == s3.charAt(count))) {
                count++;
            }

            for (int i = 0; i < count; i++) {
                // 只要有一种情况满足就成立
                if (isInterleaveWithOrder(!whitch, s1.substring(i + 1), s2, s3.substring(i + 1))) {
                    return true;
                }
            }
            return false;
        } else {
            // 先 s2
            int count = 0;
            while ((count < s2.length() && count < s3.length()) && (s2.charAt(count) == s3.charAt(count))) {
                count++;
            }

            for (int i = 0; i < count; i++) {
                if (isInterleaveWithOrder(!whitch, s1, s2.substring(i + 1), s3.substring(i + 1))) {
                    return true;
                }
            }
            return false;
        }
    }












    public boolean isValidBST(TreeNode root) {
        // 判断当前树是不是二叉搜索树 => 左根右是升序的
        try {
            mid(root);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    int record = Integer.MIN_VALUE;

    public void mid(TreeNode root) throws Exception {
        if (root != null && root.left != null) {
            mid(root.left);
        }
        // 输出根
        if (root.val < record) {
            // 不是递增，不是有效的二叉搜索树
            throw new Exception("非有效搜索二叉树");
        } else {
            record = root.val;
        }

        if (root != null && root.right != null) {
            mid(root.right);
        }
    }
}
