package com.leetcode.algorithms;

/**
 * @author wangyj
 * @Description: (二叉树的最近公共祖先)
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * @date 2021/6/23 18:32
 */
public class _236二叉树的最近公共祖先 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        //左半部分查找
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        //左右子树都找到值了(一左一右) 返回他们的根节点
        if (left != null && right != null) return root;
        //都在左边或都在右边 返回对应的左右节点
        return (left != null) ? left : right;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
