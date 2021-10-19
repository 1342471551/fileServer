package com.algorithm.means.dynamic;

import java.util.Arrays;

/**
 * @author wangyj
 * @Description: (最长重复子数组) 动态规划
 * 初始值： dp[i,0]=0 dp[0,j]=0
 * 状态转移方程： dp[i,j]=以nums1[i-1],nums2[j-1]结尾的公共子串的长度
 * nums1[i-1]=nums[j-1] 则 dp[i,j]=dp[i-1,j-1]+1
 * nums1[i-1]≠nums[j-1] 则 dp[i,j]=0 结尾都不一样肯定为0
 * @date 2021/6/10 19:47
 */
public class _718最长重复子数组 {

    public static int findLength(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) return 0;
        //数组也是从0开始的 所以定义需要长度+1
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        int max = 0;
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums1={1,2,4,5}; int[] nums2={2,4,1,3};
        findLength(nums1,nums2);
    }
}
