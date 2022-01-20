package com.leetcode.exercise;

import java.util.Arrays;

/**
 * @author wangyj
 * @Description: (数组中最大数对和的最小值)
 * https://leetcode-cn.com/problems/minimize-maximum-pair-sum-in-array/
 * @date 2021/7/20 9:07
 */
public class _1877数组中最大数对和的最小值 {

    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int length = nums.length;
        int len = length >> 1;
        int ans = 0;
        for (int i = 0; i < len; i++) {
            ans = Math.max(nums[i] + nums[length - 1 - i], ans);
        }
        return ans;
    }
}
