package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (保持城市天际线)
 * https://leetcode-cn.com/problems/max-increase-to-keep-city-skyline/
 * @date 2021/12/13 10:06
 */
public class _807保持城市天际线 {

    public int maxIncreaseKeepingSkyline(int[][] grid) {
        //竖直方向
        int[] vertical = new int[grid[0].length];
        //水平方向
        int[] transverse = new int[grid.length];

        //初始化脚本
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] > vertical[j]) vertical[j] = grid[i][j];
                if (grid[i][j] > transverse[i]) transverse[i] = grid[i][j];
            }
        }
        //计算可添加高度
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                res += Integer.min(vertical[j], transverse[i]) - grid[i][j];
            }
        }
        return res;
    }
}
