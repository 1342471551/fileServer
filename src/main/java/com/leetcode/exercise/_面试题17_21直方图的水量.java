package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (直方图的水量)
 * https://leetcode-cn.com/problems/volume-of-histogram-lcci/
 * @date 2021/4/2 8:55
 */
public class _面试题17_21直方图的水量 {

    public static int trap(int[] height) {
        int n = height.length;
        if (n <= 2) return 0;
        //从左往右遍历，最高值
        int[] left = new int[n];
        left[0] = height[0];
        //从右往左遍历，最高值
        int[] right = new int[n];
        right[n - 1] = height[n - 1];
        for (int i = 1; i < n; i++) {
            left[i] = Math.max(left[i - 1], height[i]);
            right[n - i - 1] = Math.max(right[n - i], height[n - i - 1]);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            //平扫了整个数组,用左右两个的低值减去数组的面积就是存水量
            res += Math.min(left[i], right[i]) - height[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int trap = trap(height);
        System.out.println(trap);
    }

}
