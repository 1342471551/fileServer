package com.algorithm.means.dynamic;

/**
 * @author wangyj
 * @Description: (兑换硬币) 动态规划
 * @date 2021/6/7 10:48
 * 动态规划步骤
 * 1.定义状态dp[n](定义原问题和子问题的解)
 * 2.设置初始状态(边界) 设置dp[0]的值
 * 3.确定状态转移方程(确定dp[i]与dp[i-1]的关系)
 */
public class CoinChange {

    static Integer[] faces = {25, 10, 5, 1};

    static int coins(int n, Integer[] faces) {
        if (n < 1 || faces == null || faces.length == 0) return -1;
        //记录数组中每一个位置需要的最小面值硬币
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int face : faces) {
                if (i < face || dp[i - face] < 0) continue;
                //i枚硬币的时候,减去当前面值至少需要多少枚硬币
                min = Math.min(dp[i - face], min);
            }
            //i枚硬币需要的最少枚数是
            if (min == Integer.MAX_VALUE) {
                dp[i] = -1;
            } else {
                dp[i] = min + 1;
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int coins = coins(50, faces);
        System.out.println(coins);
    }
}
