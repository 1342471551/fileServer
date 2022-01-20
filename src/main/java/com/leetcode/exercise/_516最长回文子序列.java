package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (最长回文子序列)
 * https://leetcode-cn.com/problems/longest-palindromic-subsequence/
 * @date 2021/8/12 3:41 下午
 */
public class _516最长回文子序列 {
    /**
     * dp[i][j]在i～j范围之内最长子序列
     * dp[i][i]=1
     * 若s[i]=s[j] dp[i][j]=dp[i+1][j-1]+2
     * 若s[i]!=s[j] dp[i][j]=max(dp[i+1][j],dp[i][j-1])
     */
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            char c1 = s.charAt(i);
            for (int j = i + 1; j < n; j++) {
                char c2 = s.charAt(j);
                if (c1 == c2) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
