package com.leetcode.exercise;

import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wangyj
 * @Description: (叶子相似的树)
 * https://leetcode-cn.com/problems/leaf-similar-trees/
 * @date 2021/5/10 16:46
 */
public class _872叶子相似的树 {

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        String str1 = myfun(root1, "");
        String str2 = myfun(root2, "");
        return str1.equals(str2);
    }

    public String myfun(TreeNode root, String str) {
        if (root == null) {
            return str;
        }
        if (root.right == null && root.left == null) {
            str += '-';
            str = str + root.val;
            return str;
        }
        return myfun(root.left, str) + myfun(root.right, str);
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
}
