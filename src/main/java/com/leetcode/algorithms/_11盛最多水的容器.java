package com.leetcode.algorithms;

/**
 * @author wangyj
 * @Description: (盛最多水的容器)
 * https://leetcode-cn.com/problems/container-with-most-water/
 * @date 2021/7/8 11:49
 */
public class _11盛最多水的容器 {

    //左右指针小的向中间移动O(n)解决 若大的向中间移动宽度减少必然不是正确答案
    public int maxArea(int[] height) {
        if (height == null || height.length == 0) return 0;
        int l = 0, r = height.length - 1, res = 0;
        while (l < r) {
            if (height[l] <= height[r]) {
                res = Math.max(res, (r - l) * height[l]);
                l++;
            } else {
                res = Math.max(res, (r - l) * height[r]);
                r--;
            }
        }
        return res;
    }
}
