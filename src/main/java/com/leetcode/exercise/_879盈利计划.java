package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (盈利计划)
 * https://leetcode-cn.com/problems/profitable-schemes/
 * @date 2021/6/9 9:25
 */
public class _879盈利计划 {

    /**
     * @param n         人员
     * @param minProfit 至少的盈利
     * @param group     需要参与的人员
     * @param profit    利润
     * @return 返回种类
     * 动态规划 三个维度分别为：当前可选择的工作，已选择的小组员工人数，以及目前状态的工作获利下限。
     * dp[i][j][k] 表示在前i个工作中选择了j个员工，并且满足工作利润至少为k的情况下的盈利计划的总数目
     * dp[0][0][0]=1
     */
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        // 前i份工作选择了j个员工得到工作利润为minProfit的数目
        int[][][] dp = new int[group.length + 1][n + 1][minProfit + 1];

        dp[0][0][0] = 1;
        int Mod = (int)1e9 + 7;

        for(int i = 1; i <= group.length; i++){
            // 当前工程需要人数
            int people = group[i - 1];
            // 当前工程获得的利润
            int profitNum = profit[i - 1];
            for(int j = 0; j <= n; j++){
                for(int k = 0; k <= minProfit; k++){
                    // 不选择
                    dp[i][j][k] = dp[i - 1][j][k] % Mod;
                    // 选择
                    if(j >= people){
                        dp[i][j][k] = (dp[i - 1][j - people][Math.max(k - profitNum, 0)] + dp[i - 1][j][k]) % Mod;
                    }
                }
            }
        }

        int sum = 0;
        for(int j = 0; j <= n; j++){
            sum = (sum + dp[group.length][j][minProfit]) % Mod;
        }

        return sum;
    }
}
