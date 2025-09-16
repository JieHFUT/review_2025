package test;

import org.junit.Test;

import java.util.*;

/**
 * ClassName: NietyFive
 * Package: test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/9/16 21:02
 * @Version 1.0
 */
public class NietyFive {

    public static void main(String[] args) {

        List<TreeNode> treeNodes = generateTrees(3);
        System.out.println();
    }


    public static List<TreeNode> generateTrees(int n) {
        // 求取所有可能结果，思路是使用回溯
        List<Integer> rest = new ArrayList<>();
        for(int i = 1; i <= n; i++) {
            rest.add(i);
        }

        backtrack(new TreeNode(), rest);

        List<TreeNode> res = new ArrayList<>();
        for(Map.Entry<String, TreeNode> entries : map.entrySet()) {
            res.add(entries.getValue());
        }
        return res;
    }

    static HashMap<String, TreeNode> map = new HashMap<>();

    public static void backtrack(TreeNode root, List<Integer> rest) {
        // 思考获取一个结果的时候如何进行保存这颗树
        if (rest.size() == 0) {
            // 获取了一个可能的二叉搜索树
            // 复制这棵树，然后将其保存在 set 中
            TreeNode node = copyTree(root);
            // 考虑如何进行去重，遍历一遍创造一个 key
            String key = getKey(node);
            map.put(key, node);
        }

        // 从剩下的数据中选取一个加入到二叉树中
        for (int i = 0; i < rest.size(); i++) {
            // 记录原有的树结构
            TreeNode origin = copyTree(root);
            int toAdd = rest.get(i);
            rest.remove(i);
            // 将这个 toAdd 添加到二叉树中
            TreeNode node =  setNext(root, toAdd);

            backtrack(node, rest);

            rest.add(i, toAdd);
            root = origin;
        }
    }

    public static TreeNode copyTree(TreeNode root) {
        // 不影响原有的树，复制一颗新树
        if (root == null) return null;

        TreeNode ret = new TreeNode();
        // 根左右
        ret.val = root.val;

        ret.left = copyTree(root.left);
        ret.right = copyTree(root.right);

        return ret;
    }

    public static TreeNode setNext(TreeNode root, int toAdd) {

        TreeNode remove = root;
        // 将这个数字添加到二叉树中
        if (remove.val == 0) {
            // 说明这是添加的第一个数字
            remove.val = toAdd;
        } else {
            // 判断这个数字往哪边去
            while (true) {
                if (remove.val < toAdd) {
                    // 往右边去
                    if (remove.right == null) {
                        remove.right = new TreeNode(toAdd);
                        break;
                    } else {
                        remove = remove.right;
                    }
                } else {
                    // 往左边去
                    if (remove.left == null) {
                        remove.left = new TreeNode(toAdd);
                        break;
                    } else {
                        remove = remove.left;
                    }
                }
            }
        }
        return root;
    }

    public static String getKey(TreeNode root) {
        if (root == null || root.val == 0) return "";
        StringBuilder ret = new StringBuilder();
        ret.append(root.val);

        ret.append(getKey(root.left));
        ret.append(getKey(root.right));
        return ret.toString();
    }



}
