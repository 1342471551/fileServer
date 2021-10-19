package com.leetcode.exercise;

import java.util.Arrays;

/**
 * @author wangyj
 * @Description: (最长递增子序列)
 * @date 2021/7/20 20:18
 */
public class _300最长递增子序列 {
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if (len < 2) return len;
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        int res = dp[0];
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
