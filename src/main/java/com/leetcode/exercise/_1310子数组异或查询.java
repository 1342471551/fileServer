package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (子数组异或查询)
 * https://leetcode-cn.com/problems/xor-queries-of-a-subarray/
 * @date 2021/5/12 9:59
 */
public class _1310子数组异或查询 {

    public int[] xorQueries(int[] arr, int[][] queries) {
        int[] a = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            a[i] = arr[query[0]];
            for (int j = query[0] + 1; j <= query[1]; j++) {
                a[i] ^= arr[j];
            }
        }
        return a;
    }
}
