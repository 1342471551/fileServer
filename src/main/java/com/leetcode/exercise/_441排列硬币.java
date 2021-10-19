package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (排列硬币)
 * https://leetcode-cn.com/problems/arranging-coins/
 * @date 2021/10/10 9:52
 */
public class _441排列硬币 {

    public int arrangeCoins(int n) {
        int res = 0, i = 1;
        while (n >= i) {
            n = n - i;
            i++;
            res++;
        }
        return res;
    }


    public static void main(String[] args) {
        _441排列硬币 s=new _441排列硬币();
        System.out.println(s.arrangeCoins(10));
    }
}
