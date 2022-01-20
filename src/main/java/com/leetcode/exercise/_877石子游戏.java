package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (石子游戏)
 * https://leetcode-cn.com/problems/stone-game/
 * @date 2021/6/16 11:31
 */
public class _877石子游戏 {

    /**
     * 动态规划
     * dp[i][j] 表示剩下的石子堆为下标i到下标j时当前玩家与另一个玩家的石子数量之差的最大值(i<=j)
     * 当i>j(无意义) dp[i][j]=0
     * dp[i][i]=piles[i](只剩下一堆石子了)
     * p[i][j]=max(piles[i]−dp[i+1][j],piles[j]−dp[i][j−1])
     * 最后判断dp[0][piles.length−1]值是否大于0
     */
    public boolean stoneGame(int[] piles) {
        int length = piles.length;
        int[][] dp = new int[length][length];
        //定义初始变量(当只剩一堆石子的情况)
        for (int i = 0; i < length; i++) {
            dp[i][i] = piles[i];
        }
        //j必须大于i 否则无意义 i需要倒序(i需要用到i+1的值)j需要正序(j需要用到j-1的值)
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }
        return dp[0][length - 1] > 0;
    }
}
