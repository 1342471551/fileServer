package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (完全平方数)
 * https://leetcode-cn.com/problems/perfect-squares/
 * @date 2021/6/11 14:34
 */
public class _279完全平方数 {

    /**
     * 动态规划
     * 定义状态数组：dp[n] 数n的最小完全平方数
     * 定义初始化状态：dp[0]=0
     * 定义状态转换方程：dp[i]=min(dp[i-j*j])+1
     */
    public static int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                min = Math.min(min, dp[i - j * j]);
            }
            dp[i] = min + 1;
        }
        return dp[n];
    }
}
