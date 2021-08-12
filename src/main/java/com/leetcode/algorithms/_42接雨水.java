package com.leetcode.algorithms;

/**
 * @author wangyj
 * @Description: (接雨水)
 * https://leetcode-cn.com/problems/trapping-rain-water/
 * @date 2021/7/8 15:53
 */
public class _42接雨水 {

    public int trap(int[] height) {
        if (height == null || height.length < 1) return 0;
        int[] lCapacity = new int[height.length];
        int[] rCapacity = new int[height.length];
        //左边扫描
        lCapacity[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            lCapacity[i] = Math.max(lCapacity[i - 1], height[i]);
        }
        //右边扫描
        rCapacity[height.length - 1] = height[height.length - 1];
        for (int j = height.length - 2; j >= 0; j--) {
            rCapacity[j] = Math.max(rCapacity[j + 1], height[j]);
        }
        int res = 0;
        for (int x = 0; x < height.length; x++) {
            res += Math.min(lCapacity[x], rCapacity[x]) - height[x];
        }
        return res;
    }
}