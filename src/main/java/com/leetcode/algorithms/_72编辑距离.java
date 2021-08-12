package com.leetcode.algorithms;

/**
 * @author wangyj
 * @Description: (编辑距离)
 * https://leetcode-cn.com/problems/edit-distance/
 * @date 2021/6/22 20:26
 */
public class _72编辑距离 {
    /**
     * 动态规划
     * dp[0][0]=0 dp[0][j] dp[i][0]可知
     * dp[i][j]=word1[0,i)转换word2[0,j)的最小操作次数
     */
    public int minDistance(String word1, String word2) {
        int length1 = word1.length();
        int length2 = word2.length();
        char[] char1 = word1.toCharArray();
        char[] char2 = word2.toCharArray();
        int[][] dp = new int[length1 + 1][length2 + 1];
        //初始化状态
        for (int row = 1; row <= length1; row++) {
            dp[row][0] = row;
        }
        for (int col = 1; col <= length2; col++) {
            dp[0][col] = col;
        }
        //四种情况选择最小的
        //1. 删除待转换串最后一个字符加上删除后的最小操作数 dp[i][j]=1+dp[i-1][j]
        //2. 先不要转换后的最后一个字符,最后进行插入操作 dp[i][j]=dp[i][j-1]+1
        //3. 最后一个字符不相等,则最后一个字符进行替换操作 dp[i][j]=dp[i-1][j-1]+1
        //4. 若最后一个字符相等 则不需要操作 dp[i][j]=dp[i-1][j-1]
        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                int change1 = dp[i - 1][j] + 1;
                int change2 = dp[i][j - 1] + 1;
                int change3;
                if (char1[i - 1] == char2[j - 1]) {
                    change3 = dp[i - 1][j - 1];
                } else {
                    change3 = dp[i - 1][j - 1] + 1;
                }
                dp[i][j] = Math.min(Math.min(change1, change2),change3);
            }
        }

        return dp[length1][length2];
    }
}
