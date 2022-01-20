package com.leetcode.exercise;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wangyj
 * @Description: (搜索树的范围和)
 * https://leetcode-cn.com/problems/range-sum-of-bst/
 * @date 2021/4/27 14:41
 */
public class _938二叉搜索树的范围和 {

    public int rangeSumBST(TreeNode root, int low, int high) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int sum = 0;
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (poll.val < low) {
                if (poll.right != null) queue.offer(poll.right);
            } else if (poll.val > high) {
                if (poll.left != null) queue.offer(poll.left);
            } else {
                if (poll.left != null) queue.offer(poll.left);
                if (poll.right != null) queue.offer(poll.right);
                sum += poll.val;
            }
        }
        return sum;
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
