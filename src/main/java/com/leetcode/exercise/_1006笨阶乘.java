package com.leetcode.exercise;

/**
 * @author wangyj
 * https://leetcode-cn.com/problems/clumsy-factorial/
 * @Description: (笨阶乘)
 * @date 2021/4/1 15:57
 */
public class _1006笨阶乘 {

    /**
     * 思路:
     * 当N<4单独算出答案,最后递归进行相减
     * 当N>4先算出首结果四个为一组进行循环相加减
     * 最后得出结果
     */
    public static int clumsy(int N) {
        if (N <= 2) {
            return N;
        }
        if (N == 3) {
            return 6;
        }
        int sum = N * (--N) / (--N) + (--N);
        while (--N >= 4) {
            sum += (-N * (--N) / (--N) + (--N));
        }
        return sum - clumsy(N);
    }

}
