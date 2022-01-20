package com.leetcode.exercise;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

/**
 * @author wangyj
 * @Description: (猜数字大小II)
 * https://leetcode-cn.com/problems/guess-number-higher-or-lower-ii/
 * @date 2021/11/12 4:31 下午
 */
public class _375猜数字大小II {

    int[][] dp;

    /**
     * 区间dp
     * dp[i][j]:在[i,j]之间猜数最少花费
     * 枚举先猜k，则花费k，剩下区间[i,k-1]和[k+1,j]，因为会告诉你大了或小了，所以肯定只选择一个，又因为我们要保证猜对，所以取max{dp[i][k-1],dp[k+1][j]} ，即有：
     * dp[i][j]=min(dp[i][j], k+max(dp[i][k-1],dp[k+1][j]));
     */
    public int getMoneyAmount(int n) {
        dp = new int[500][500];
        for (int j = 1; j <= n; j++) {
            for (int i = j - 1; i >= 1; i--) {
                //初始成极大值
                dp[i][j] = n * n;
                for (int k = i; k <= j; k++) {
                    dp[i][j] = min(dp[i][j], k + max(dp[i][k - 1], dp[k + 1][j]));
                }
            }
        }
        return dp[1][n];
    }
}
