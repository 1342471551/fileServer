package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (第N位数字)
 * https://leetcode-cn.com/problems/nth-digit/
 * @date 2021/11/30 11:09
 */
public class _400第N位数字 {

    public int findNthDigit(int n) {
        int d = 1, count = 9;
        //计算出n属于哪一个位数上
        while (n > (long) d * count) {
            n -= d * count;
            d++;
            count *= 10;
        }
        int index = n - 1;
        int start = (int) Math.pow(10, d - 1);
        int num = start + index / d;
        int digitIndex = index % d;
        int digit = (num / (int) (Math.pow(10, d - digitIndex - 1))) % 10;
        return digit;
    }
}
