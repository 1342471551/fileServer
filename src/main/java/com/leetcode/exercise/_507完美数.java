package com.leetcode.exercise;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author wangyj
 * @Description: (完美数)
 * https://leetcode-cn.com/problems/perfect-number/
 * @date 2021/12/31 10:05
 */
public class _507完美数 {

    public boolean checkPerfectNumber(int num) {
        if (num == 1) return false;
        int sum = 1;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                sum += i;
                if (i * i < num) sum += (num / i);
            }
        }
        return sum == num;
    }
}
