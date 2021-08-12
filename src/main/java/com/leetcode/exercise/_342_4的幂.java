package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (4的幂)
 * https://leetcode-cn.com/problems/power-of-four/
 * @date 2021/5/31 18:18
 */
public class _342_4的幂 {

    //n & (n - 1)可以判断是否为2的幂
    public boolean isPowerOfFour1(int n) {
        //0xaaaaaaaa = 10101010101010101010101010101010 (偶数位为1，奇数位为0）
        return n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa) == 0;
    }

    //n & (n - 1)可以判断是否为2的幂 当n是2的幂但是不是4的幂对3取模余数为2 当余数为1则表示也是4的幂
    public boolean isPowerOfFour2(int n) {
        return n > 0 && (n & (n - 1)) == 0 && n % 3 == 1;
    }
}
