package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (两整数之和)
 * https://leetcode-cn.com/problems/sum-of-two-integers/
 * @date 2021/9/26 11:55
 */
public class _371两整数之和 {

    /**
     * 0+0=0，0+1=1，1+0=1，1+1=0（进位）
     * 结果等价于异或^
     * 其中进位标识carry作用于下一位next，相当于next^carry，通过(a&b)<<1先求出所有相加需要进位的bit，再左移一位作用于下一位。
     */
    public int getSum(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }
        return a;
    }

}
