package com.leetcode.algorithms;

/**
 * @author wangyj
 * @Description: (买卖股票的最佳时机)
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 * @date 2021/6/22 9:14
 */
public class _121买卖股票的最佳时机 {

    public int maxProfit(int[] prices) {
        int max = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            max = Math.max(max, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return max;
    }

    //动态规划
    public int maxProfit1(int[] prices) {
        int[] price = new int[prices.length];
        int[] dp = new int[prices.length];
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            price[i] = prices[i] - prices[i - 1];
            dp[i] = dp[i - 1] > 0 ? dp[i - 1] + price[i] : price[i];
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
