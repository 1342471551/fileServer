package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (最后一块石头的重量II)
 * https://leetcode-cn.com/problems/last-stone-weight-ii/
 * @date 2021/6/8 11:49
 */
public class _1049最后一块石头的重量II {

    //转换成01背包问题，求两堆石头的最小差值。由于石头总和为sum.则问题转换成了
    //背包最多装sum / 2的石头,stones数组里有一大堆石头。求如何装能装下最多重量石头。
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int weight : stones) {
            sum += weight;
        }
        int m = sum / 2;
        boolean[] dp = new boolean[m + 1];
        dp[0] = true;
        for (int weight : stones) {
            for (int j = m; j >= weight; --j) {
                //循环判断当前dp[j]最终存放是否为true
                dp[j] = dp[j] || dp[j - weight];
            }
        }
        for (int j = m;; --j) {
            //从中间重量进行循环计算 算出第一个dp[j]为true的重量返回
            if (dp[j]) {
                return sum - 2 * j;
            }
        }
    }
}
