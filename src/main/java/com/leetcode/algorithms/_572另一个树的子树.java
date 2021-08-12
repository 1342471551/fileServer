package com.leetcode.algorithms;

/**
 * @author wangyj
 * @Description: (另一个树的子树)
 * https://leetcode-cn.com/problems/subtree-of-another-tree/
 * @date 2021/7/16 16:25
 */
public class _572另一个树的子树 {

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null || subRoot == null) return false;
        return postSerialize(root).contains(postSerialize(subRoot));
    }

    /**
     * 利用后续遍历进行序列化
     * 有数则返回x！ 空节点返回#！
     */
    private String postSerialize(TreeNode root) {
        StringBuilder stringBuilder = new StringBuilder();
        postSerialize(root, stringBuilder);
        return stringBuilder.toString();
    }

    private void postSerialize(TreeNode node, StringBuilder sb) {
        if (node.left == null) {
            sb.append("#!");
        } else {
            postSerialize(node.left, sb);
        }
        if (node.right == null) {
            sb.append("#!");
        } else {
            postSerialize(node.right, sb);
        }
        sb.append(node.val).append("!");
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
