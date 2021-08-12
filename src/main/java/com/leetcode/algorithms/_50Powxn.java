package com.leetcode.algorithms;

/**
 * @author wangyj
 * @Description: (Pow ( x, n))
 * https://leetcode-cn.com/problems/powx-n/
 * @date 2021/7/5 15:04
 */
public class _50Powxn {

    public double myPow(double x, int n) {
        return Math.pow(x, n);
    }

    public double myPow1(double x, int n) {
        if (n == 0) return 1;
        if (n == -1) return 1 / x;
        //是否为奇数
        boolean odd = (n & 1) == 1;
        double half = myPow1(x, n >> 1);
        half *= half;
        //是奇数需要进行相乘一下
        return odd ? (half * x) : half;
    }
}
