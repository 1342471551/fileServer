package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (数字的补数)
 * https://leetcode-cn.com/problems/number-complement/
 * @date 2021/10/18 17:29
 */
public class _476数字的补数 {

    public int findComplement1(int num) {
        int temp = num, c = 0;
        while (temp > 0) {
            //算出有多少位
            temp >>= 1;
            //与全部是1的位数进行异或1 0进行转换
            c = (c << 1) + 1;
        }
        return num ^ c;
    }
}
