package com.leetcode.algorithms;

/**
 * @author wangyj
 * @Description: (整数反转)
 * @date 2021/7/8 10:04
 */
public class _7整数反转 {

    //每次取(数字最后一位)*10+当前最后一位 进行循环操作
    //123= ((3*10)+2)*10+1
    public int reverse(int x) {
        long res = 0;
        while (x != 0) {
            res = res * 10 + x % 10;
            x /= 10;
        }
        //判断溢出
        if (res>Integer.MAX_VALUE) return 0;
        if (res<Integer.MIN_VALUE) return 0;
        return (int) res;
    }
}
