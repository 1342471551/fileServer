package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (一维数组的动态和)
 * https://leetcode-cn.com/problems/running-sum-of-1d-array/
 * @date 2021/8/31 4:43 下午
 */
public class _1480一维数组的动态和 {

    public static int[] runningSum(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return nums;
        }
        int sum = nums[0];
        for (int i = 1; i < len; i++) {
            sum += nums[i];
            nums[i] = sum;
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] a={1,2,3,4};
        runningSum(a);
    }
}
