package com.algorithm.means.dynamic;

import java.util.Arrays;

/**
 * @author wangyj
 * @Description: (最大子序和) 动态规划解决
 * https://leetcode-cn.com/problems/maximum-subarray/
 * 1.定义状态dp[n](定义原问题和子问题的解)
 * 2.设置初始状态(边界) 设置dp[0]的值
 * 3.确定状态转移方程(确定dp[i]与dp[i-1]的关系)
 * @date 2021/6/4 17:05
 */
public class _53最大子序和 {

    /**
     * 动态规划 空间:O(n) 时间:O(n)
     * dp(i) 以nums[i]结尾的最大连续子序列和
     * if (dp(i-1)<=0) dp(i)=nums[i]
     * else dp[i]=dp[i-1]+nums[i]
     */
    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] <= 0) {
                dp[i] = nums[i];
            } else {
                dp[i] = dp[i - 1] + nums[i];
            }
        }
        dp = Arrays.stream(dp).sorted().toArray();
        return dp[nums.length-1];
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        maxSubArray(nums);
    }
}
