package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (二叉搜索树中的搜索)
 * https://leetcode-cn.com/problems/search-in-a-binary-search-tree/
 * @date 2021/11/26 14:50
 */
public class _700二叉搜索树中的搜索 {

    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode node = root;
        while (node != null) {
            if (node.val == val) return node;
            else if (node.val > val) node = node.left;
            else node = node.right;
        }
        return null;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
