package com.leetcode.exercise;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wangyj
 * @Description: (二叉树中第二小的节点)
 * https://leetcode-cn.com/problems/second-minimum-node-in-a-binary-tree/
 * @date 2021/7/27 8:31
 */
public class _671二叉树中第二小的节点 {

    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) return -1;
        Queue<TreeNode> queue = new LinkedList<>();
        Long a = Long.MAX_VALUE,b=Long.MAX_VALUE;
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (a > node.val) {
                b = a;
                a = (long) node.val;
            } else if (a == node.val) {

            } else if (b >= node.val) {
                b = (long)node.val;
            }
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        return b == Long.MAX_VALUE ? -1 : b.intValue();
    }

    public int findSecondMinimumValue1(TreeNode root) {
        return myfun(root, root.val);
    }

    public int myfun(TreeNode root, int val) {
        if (root == null) {
            return -1;
        }
        //找到相对较小的树,对于大的一边直接返回
        if (root.val > val) {
            return root.val;
        }
        int l = myfun(root.left, val);
        int r = myfun(root.right, val);
        //说明两个子树存在大于最小值的节点,返回较小的那一个
        if (l > val && r > val) {
            return Math.min(l,r);
        }
        //返回相对较小的树的相对较大值(第二小值)
        return Math.max(l,r);
    }

    public static class TreeNode {
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


    public static void main(String[] args) {
        _671二叉树中第二小的节点 a = new _671二叉树中第二小的节点();
        TreeNode node = new TreeNode(2, new TreeNode(2, null, null), new TreeNode(2, null, null));
        System.out.println(a.findSecondMinimumValue(node));
    }
}
