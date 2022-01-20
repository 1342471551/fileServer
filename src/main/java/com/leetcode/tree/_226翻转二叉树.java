package com.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wangyj
 * @Description: (翻转二叉树)
 * https://leetcode-cn.com/problems/invert-binary-tree/
 * @date 2021/4/6 19:40
 */
public class _226翻转二叉树 {

    //利用前序遍历
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    //利用层序遍历
    public TreeNode invertTreeByLevel(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            TreeNode left = poll.left;
            poll.left = poll.right;
            poll.right = left;
            //上面左右交换了 所以先右再左
            if (poll.right != null) queue.offer(poll.right);
            if (poll.left != null) queue.offer(poll.left);
        }
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
