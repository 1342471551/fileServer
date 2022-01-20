package com.leetcode.exercise;

import java.util.TreeSet;

/**
 * @author wangyj
 * @Description: (矩形区域不超过K的最大数值和)
 * https://leetcode-cn.com/problems/max-sum-of-rectangle-no-larger-than-k/
 * @date 2021/4/22 15:01
 */
public class _363矩形区域不超过K的最大数值和 {
    public static int maxSumSubmatrix(int[][] matrix, int k) {
        int ans = Integer.MIN_VALUE;
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; ++i) { // 枚举上边界
            int[] sum = new int[n];
            for (int j = i; j < m; ++j) { // 枚举下边界
                for (int c = 0; c < n; ++c) {
                    sum[c] += matrix[j][c]; // 更新每列的元素和
                }
                TreeSet<Integer> sumSet = new TreeSet<Integer>();
                sumSet.add(0);
                int s = 0;
                for (int v : sum) {
                    s += v;
                    Integer ceil = sumSet.ceiling(s - k);
                    if (ceil != null) {
                        ans = Math.max(ans, s - ceil);
                    }
                    sumSet.add(s);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] matrix = {{2, 4, 6}, {3, 5, 7}};
        System.out.println(matrix.length);
        System.out.println(matrix[0].length);
    }
}
