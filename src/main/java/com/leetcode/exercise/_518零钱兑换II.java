package com.leetcode.exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author wangyj
 * @Description: (零钱兑换II)
 * https://leetcode-cn.com/problems/coin-change-2/
 * @date 2021/6/10 9:24
 */
public class _518零钱兑换II {

    public static int change(int amount, int[] coins) {

        // 动态规划
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        //循环不通面值硬币算出当前硬币凑成的个数
        for (int coin : coins) {
            //循环金钱,累加一下兑换可能性
            for (int i = 0; i + coin <= amount; i++) {
                dp[i + coin] += dp[i];
            }
        }
        return dp[amount];
    }

}
