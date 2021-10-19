package com.algorithm.means.dynamic;

/**
 * @author wangyj
 * @Description: (最长公共子序列) 动态规划
 * https://leetcode-cn.com/problems/longest-common-subsequence/
 * @date 2021/6/8 9:06
 */
public class _1143最长公共子序列 {

    /**
     * dp(0,j),dp(i,0)初始值为0
     * dp(i,j) text1第i个元素和text2第j个元素的公共子序列
     * 状态转移方程:若nums[i-1]=nums[j-1] dp(i,j)=dp(i-1,j-1)+1
     * nums[i-1]≠nums[j-1] dp(i,j)= Math.max(dp(i-1,j),dp(i,j-1))
     * dp(text1.length,text2.length)是动态规划最终解
     */
    public int longestCommonSubsequence(String text1, String text2) {
        char[] nums1 = text1.toCharArray();
        char[] nums2 = text2.toCharArray();
        if (nums1.length == 0 || nums2.length == 0) return 0;
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[nums1.length][nums2.length];
    }

    //使用滚动数组优化内存空间
    public int longestCommonSubsequence1(String text1, String text2) {
        char[] nums1 = text1.toCharArray();
        char[] nums2 = text2.toCharArray();
        if (nums1.length == 0 || nums2.length == 0) return 0;
        //滚动使用两行数组完成动态规划前面的数组存值
        int[][] dp = new int[2][nums2.length + 1];
        for (int i = 1; i <= nums1.length; i++) {
            int row = i & 1;
            int prevRow = (i - 1) & 1;
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[row][j] = dp[prevRow][j - 1] + 1;
                } else {
                    dp[row][j] = Math.max(dp[prevRow][j], dp[row][j - 1]);
                }
            }
        }
        return dp[nums1.length][nums2.length];
    }





    //递归调用
    public int lcs(String text1, String text2) {
        char[] nums1 = text1.toCharArray();
        char[] nums2 = text2.toCharArray();
        if (nums1.length == 0 || nums2.length == 0) return 0;
        return lcs(nums1, nums1.length, nums2, nums2.length);
    }

    //求nums1前i个元素与nums2前j个元素的最长子序列 递归实现
    static int lcs(char[] nums1, int i, char[] nums2, int j) {
        //初始化递归状态
        if (i == 0 || j == 0) return 0;
        //最后一个元素相等
        if (nums1[i - 1] == nums2[j - 1]) {
            return lcs(nums1, i - 1, nums2, j - 1) + 1;
        }
        //最后一个元素不相等
        return Math.max(lcs(nums1, i - 1, nums2, j), lcs(nums1, i, nums2, j - 1));
    }
}
