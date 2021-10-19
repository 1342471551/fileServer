package com.leetcode.algorithms;

/**
 * @author wangyj
 * @Description: (恢复二叉搜索树)
 * https://leetcode-cn.com/problems/recover-binary-search-tree/
 * @date 2021/6/25 10:19
 */
public class _99恢复二叉搜索树 {

    //上一次中序遍历的节点(前驱节点)
    private TreeNode prev;

    //记录出现的两个逆序节点
    private TreeNode first;
    private TreeNode second;

    //传入的是个错误二叉树 有两个节点被错误交换
    public void recoverTree(TreeNode root) {
        findWrongNodes(root);
        //进行错误节点数据交换
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }

    //中序遍历 发现前一个值比当前值大说明出现了逆序节点
    private void findWrongNodes(TreeNode root) {
        if (root == null) return;
        findWrongNodes(root.left);

        //出现了逆序节点
        if (prev != null && prev.val > root.val) {
            //若还没发现较大逆序对,把发现的逆序对赋值给他
            if (first == null) first = prev;
            //最后一个逆序对较小的节点赋值给他
            second = root;
        }
        //把这个当作下一次遍历的前一个节点
        prev = root;

        findWrongNodes(root.right);
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
