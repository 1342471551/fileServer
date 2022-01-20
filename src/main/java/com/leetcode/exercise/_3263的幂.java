package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (3的幂)
 * https://leetcode-cn.com/problems/power-of-three/
 * @date 2021/9/23 11:22
 */
public class _3263的幂 {

    public boolean isPowerOfThree(int n) {
        if (n < 1) return false;
        int res = 0;
        while (n > 1) {
            if (res != n % 3) return false;
            n /= 3;
        }
        return true;
    }
}
