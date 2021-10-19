package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (奇怪的打印机)
 * https://leetcode-cn.com/problems/strange-printer/
 * @date 2021/5/24 8:32
 */
public class _664奇怪的打印机 {

    public int strangePrinter(String s) {
        int n = s.length();
        int[][] f = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            f[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    f[i][j] = f[i][j - 1];
                } else {
                    int minn = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        //不一样则取i-k的次数加上k-j的次数的最小值当作i-j的最小值
                        minn = Math.min(minn, f[i][k] + f[k + 1][j]);
                    }
                    f[i][j] = minn;
                }
            }
        }
        return f[0][n - 1];
    }

}
