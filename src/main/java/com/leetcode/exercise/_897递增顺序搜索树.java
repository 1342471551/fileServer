package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (递增顺序搜索树)
 * https://leetcode-cn.com/problems/increasing-order-search-tree/
 * @date 2021/4/25 16:03
 */
public class _897递增顺序搜索树 {

    TreeNode head;

    public TreeNode increasingBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.right = increasingBST(root.right);
        if (root.left != null) {
            TreeNode node = root.left;
            root.left = null;
            head = node;
            while (node.right != null)
                node = node.right;
            node.right = root;
            return increasingBST(head);
        } else
            return root;
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
