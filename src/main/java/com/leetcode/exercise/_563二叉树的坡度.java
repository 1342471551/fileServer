package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (二叉树的坡度)
 * https://leetcode-cn.com/problems/binary-tree-tilt/
 * @date 2021/11/18 3:30 下午
 */
public class _563二叉树的坡度 {

    int res;

    /**
     * 用一个成员变量在dfs过程中记录每一个当前结点左子树所有结点和减右子树所有结点和的结果，dfs返回值是当前子树的所有结点值
     */
    public int findTilt(TreeNode root) {
        this.res = 0;
        dfs(root);
        return res;
    }

    public int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = dfs(node.left);
        int right = dfs(node.right);
        res += Math.abs(left - right);
        return left + right + node.val;
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
