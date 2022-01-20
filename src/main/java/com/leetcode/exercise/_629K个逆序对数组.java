package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (K个逆序对数组)
 * https://leetcode-cn.com/problems/k-inverse-pairs-array/
 * @date 2021/11/11 3:24 下午
 */
public class _629K个逆序对数组 {

    /**
     * f[i][j]长度i的数组恰好包含j个逆序对方案组 f[0][0]=1
     * 假设选取数字k(0<k<i)逆序对包含 0~k-1 逆序对对个数 + k与0～k-1产生的逆序对个数
     * f[i][j]=f[i][j−1]−f[i−1][j−i]+f[i−1][j]
     * 最终答案是 f[n][k]
     */
    public int kInversePairs(int n, int k) {
        final int MOD = 1000000007;
        //2行节约空间滚动计算
        int[][] f = new int[2][k + 1];
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= k; ++j) {
                int cur = i & 1, prev = cur ^ 1;
                //若j-1小于零则逆序对为0
                f[cur][j] = (j - 1 >= 0 ? f[cur][j - 1] : 0) - (j - i >= 0 ? f[prev][j - i] : 0) + f[prev][j];
                if (f[cur][j] >= MOD) {
                    f[cur][j] -= MOD;
                } else if (f[cur][j] < 0) {
                    f[cur][j] += MOD;
                }
            }
        }
        return f[n & 1][k];
    }
}
