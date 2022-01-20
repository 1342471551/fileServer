package com.leetcode.exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyj
 * @Description: (二叉树寻路)
 * https://leetcode-cn.com/problems/path-in-zigzag-labelled-binary-tree/
 * @date 2021/7/29 8:58
 */
public class _1104二叉树寻路 {

    //第i行有 2^{i-1}个节点，最左边的节点标号为 2^{i-1}，最右边的节点标号为 2^i-1
    public static List<Integer> pathInZigZagTree(int label) {
        List<Integer> res = new ArrayList<>();
        //算出label的层数
        int depth = (int) (Math.log(label) / Math.log(2)) + 1;     // ln(label) / ln(2)

        // 每上一层，反转一次
        while (label > 1) {
            //在list的0号位置插入原来的元素后移
            res.add(0, label);
            //获取未翻转之前的父节点值
            label = label / 2;
            depth--;
            // 上一层最右边和最左边的值
            int right = (int) (Math.pow(2, depth) - 1);
            int left = (int) (Math.pow(2, depth - 1));
            // 反转这一层数字排序
            label = right - (label - left);
        }
        res.add(0, 1);      // 加入根节点

        return res;
    }

    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = pathInZigZagTree(14);
    }
}
