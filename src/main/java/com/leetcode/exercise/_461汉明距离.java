package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (汉明距离)
 * https://leetcode-cn.com/problems/hamming-distance/
 * @date 2021/5/27 8:49
 */
public class _461汉明距离 {

    public static int hammingDistance(int x, int y) {
        x = x ^ y;
        y = 0;
        while (x > 0) {
            if (x % 2 > 0) y++;
            x /= 2;
        }
        return y;
    }
}
