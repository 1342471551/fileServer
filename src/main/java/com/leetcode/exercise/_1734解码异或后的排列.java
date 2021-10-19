package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (解码异或后的排列)
 * https://leetcode-cn.com/problems/decode-xored-permutation/
 * @date 2021/5/11 10:46
 */
public class _1734解码异或后的排列 {

    public int[] decode(int[] encoded) {
        int n = encoded.length + 1;
        int total = 0;
        for (int i = 1; i <= n; i++) {
            total ^= i;
        }
        int odd = 0;
        for (int i = 1; i < n - 1; i += 2) {
            odd ^= encoded[i];
        }
        //首先需得到数组perm的第一个元素（即下标为0的元素）
        //得到数组perm的全部元素的异或运算结果，以及数组perm除了perm[0]以外的全部元素的异或运算结果，即可得到perm[0]的值
        //数组perm的全部元素的异或运算结果 total: 1到n的全部正整数的异或运算结果
        //数组encoded的所有下标为奇数的元素的异或运算结果即为数组perm除了perm[0]以外的全部元素的异或运算结果
        int[] perm = new int[n];
        perm[0] = total ^ odd;
        for (int i = 0; i < n - 1; i++) {
            perm[i + 1] = perm[i] ^ encoded[i];
        }
        return perm;
    }

}
