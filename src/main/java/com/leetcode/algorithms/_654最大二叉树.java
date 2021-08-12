package com.leetcode.algorithms;

/**
 * @author wangyj
 * @Description: (最大二叉树)
 * https://leetcode-cn.com/problems/maximum-binary-tree/
 * @date 2021/7/15 11:00
 */
public class _654最大二叉树 {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return findRoot(nums, 0, nums.length);
    }

    //l,r左闭右开
    private TreeNode findRoot(int[] nums, int l, int r) {
        if (l == r) return null;
        int maxIdx = l;
        for (int i = l + 1; i < r; i++) {
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }
        TreeNode root = new TreeNode(nums[maxIdx]);
        root.left = findRoot(nums, l, maxIdx);
        root.right = findRoot(nums, maxIdx + 1, r);
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
