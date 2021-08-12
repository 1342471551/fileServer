package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (平方数之和)
 * https://leetcode-cn.com/problems/sum-of-square-numbers/
 * @date 2021/4/28 15:25
 */
public class _633平方数之和 {
    public boolean judgeSquareSum(int c) {
        int n = (int) Math.floor(Math.sqrt(c));
        for (int i = 0; i <= n; i++) {
            double cur = c - Math.pow(i, 2);
            if (Math.floor(Math.sqrt(cur)) == Math.sqrt(cur))
                return true;
        }
        return false;
    }
}
