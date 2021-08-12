package com.leetcode.algorithms;

/**
 * @author wangyj
 * @Description: (最大BST子树)
 * https://leetcode-cn.com/problems/largest-bst-subtree/
 * @date 2021/6/28 16:00
 */
public class _333最大BST子树 {

    public int largestBSTSubtree(TreeNode root) {
        return (root == null) ? 0 : getInfo(root).size;
    }

    private Info getInfo(TreeNode root) {
        if (root == null) return null;
        //左子树,右子树信息
        Info li = getInfo(root.left);
        Info ri = getInfo(root.right);

        //四种情况.root为根节点的二叉树就是BST,最大BST子树就是本身
        //1.li,ri != null &&
        // li.root == root.left && root.val > li.max &&
        // ri.root == root.right && root.val < ri.min

        //2.li!=null && ri==null &&
        //li.root==root.left && root.val>li.max

        //3.li==null && ri!=null &&
        //ri.root==root.right && root.val<ri.min

        //4.li==null && ri==null

        int leftBstSize = -1, rightBstSize = -1, max = root.val, min = root.val;
        if (li == null) {
            leftBstSize = 0;
        } else if (li.root == root.left && root.val > li.max) {
            leftBstSize = li.size;
            min = li.min;
        }
        if (ri == null) {
            rightBstSize = 0;
        } else if (ri.root == root.right && root.val < ri.min) {
            rightBstSize = ri.size;
            max = ri.max;
        }

        if (leftBstSize >= 0 && rightBstSize >= 0) {
            return new Info(root, 1 + leftBstSize + rightBstSize, max, min);
        }

        if (li != null && ri != null) return (li.size > ri.size) ? li : ri;

        return (li != null) ? li : ri;
    }


    private static class Info {
        //根节点
        public TreeNode root;
        //节点数
        public int size;
        //最大值
        public int max;
        //最小值
        public int min;

        public Info(TreeNode root, int size, int max, int min) {
            this.root = root;
            this.size = size;
            this.max = max;
            this.min = min;
        }
    }


//    public int largestBSTSubtree(TreeNode root) {
//        if (root == null) return 0;
//        if (isBST(root)) {
//            return nodeCount(root);
//        }
//        return Math.max(largestBSTSubtree(root.right), largestBSTSubtree(root.left));
//    }
//
//    private boolean isBST(TreeNode root) {
//        return false;
//    }
//
//    private int nodeCount(TreeNode root) {
//        return 0;
//    }


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
