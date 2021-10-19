package com.algorithm.means.dynamic;

import java.util.Arrays;

/**
 * @author wangyj
 * @Description: (0 - 1背包问题) 动态规划
 * 状态定义：dp[i][j]=前i件物品在j容量的时能返回的最大价值
 * 初始定义：dp[i][0]=0 dp[0][j]=0
 * 转换方程：
 * 若不选择第i件物品：dp[i][j]=dp[i-1][j]
 * 若选择第i件物品：dp[i][j]=values[i]+dp[i-1][j-weights[i]]
 * dp[i][j] = Math.max(dp[i-1][j],values[i]+dp[i-1][j-weights[i]])
 * @date 2021/6/4 15:34
 */
public class _01backpack {

    /**
     * @param values   物品价值
     * @param weights  物品重量
     * @param capacity 背包容量
     * @return 返回能装的最大价值
     */
    static int maxValue(int[] values, int[] weights, int capacity) {
        if (values == null || values.length == 0 || weights == null || weights.length == 0 || capacity <= 0) return 0;
        int[][] dp = new int[values.length + 1][capacity + 1];
        //前i件物品j的重量
        for (int i = 1; i <= values.length; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (j < weights[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(
                            dp[i - 1][j],
                            dp[i - 1][j - weights[i - 1]] + values[i - 1]);
                }
            }
        }
        return dp[values.length][capacity];
    }

    //修改为1维数组的形式
    static int maxValue1(int[] values, int[] weights, int capacity) {
        if (values == null || values.length == 0 || weights == null || weights.length == 0 || capacity <= 0) return 0;
        //用一维数组存放长度是背包重量,值为最大价值
        int[] dp = new int[capacity + 1];
        //前i件物品在j容量的价值 i行是i-1行推导出来的可以从1件物品开始循环推导价值
        for (int i = 1; i <= values.length; i++) {
            //一维数组容量需要进行倒推(防止算价值取的前面数组数据不是上一个for循环的结果(i-1推导的价值数据)而是添加第i个物品算的价值)
            for (int j = capacity; j >= 1; j--) {
                //不需要考虑j<weighs的情况 若小于则说明和i-1的一样(一维数组本身记录内容就是上一行的数据)
                if (j >= weights[i - 1]) {
                    dp[j] = Math.max(
                            dp[j],
                            dp[j - weights[i - 1]] + values[i - 1]);
                }
            }
        }
        return dp[capacity];
    }

    //返回最大值,并且容量刚好装满(只需要初始化数组为负数即可,这样累加的就是容量刚好装满条件的情况)
    static int maxValueExactly(int[] values, int[] weights, int capacity) {
        if (values == null || values.length == 0 || weights == null || weights.length == 0 || capacity <= 0) return 0;
        //用一维数组存放长度是背包重量,值为最大价值
        int[] dp = new int[capacity + 1];
        Arrays.fill(dp, Integer.MIN_VALUE);
        //前i件物品在j容量的价值 i行是i-1行推导出来的可以从1件物品开始循环推导价值
        for (int i = 1; i <= values.length; i++) {
            //一维数组容量需要进行倒推(防止算价值取的前面数组数据不是上一个for循环的结果(i-1推导的价值数据)而是添加第i个物品算的价值)
            for (int j = capacity; j >= 1; j--) {
                //不需要考虑j<weighs的情况 若小于则说明和i-1的一样(一维数组本身记录内容就是上一行的数据)
                if (j >= weights[i - 1]) {
                    dp[j] = Math.max(
                            dp[j],
                            dp[j - weights[i - 1]] + values[i - 1]);
                }
            }
        }
        return dp[capacity] < 0 ? -1 : dp[capacity];
    }

    public static void main(String[] args) {
        int[] values = {1, 2, 35, 71, 23};
        int[] weight = {3, 4, 6, 1, 22, 3};
        System.out.println(maxValue1(values, weight, 20));
    }
}
