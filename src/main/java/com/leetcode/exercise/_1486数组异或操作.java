package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (数组异或操作)
 * https://leetcode-cn.com/problems/xor-operation-in-an-array/
 * @date 2021/5/7 15:23
 */
public class _1486数组异或操作 {

    public int xorOperation(int n, int start) {
        if (n<2) return start;
        int num = start;
        for (int i = 1; i < n; i++) {
            num = num ^ (start + 2 * i);
        }
        return num;
    }
}
