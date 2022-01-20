package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (二进制中1的个数)
 * https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/
 * @date 2021/6/23 15:53
 */
public class 剑指Offer15二进制中1的个数 {

    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            if ((n & 1) == 1) count++;
            n >>= 1;
        }
        return count;
    }

    public int hammingWeight1(int n) {
        return Integer.bitCount(n);
    }

    public int hammingWeight2(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) res++;
        }
        return res;
    }
}
