package com.leetcode.algorithms;

import java.util.Stack;

/**
 * @author wangyj
 * @Description: (每日温度)
 * https://leetcode-cn.com/problems/daily-temperatures/
 * @date 2021/7/16 14:06
 */
public class _739每日温度 {

    //使用动态规划 dp[temperatures.length]=0 从后向前推导 若后一天小于前一天则找到其下标点再循环比较
    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) return null;
        int[] res = new int[temperatures.length];
        res[temperatures.length - 1] = 0;
        for (int i = temperatures.length - 2; i >= 0; i--) {
            int j = i + 1;
            while (temperatures[i] >= temperatures[j] && res[j] != 0) {
                j = j + res[j];
            }
            if (temperatures[i] < temperatures[j]) res[i] = j - i;
        }
        return res;
    }

    public int[] dailyTemperatures1(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) return null;
        int[] res = new int[temperatures.length];
        //用来保存指针下标
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < temperatures.length; i++) {
            //不能等于,需要大于才行
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                res[stack.peek()] = i - stack.peek();
                stack.pop();
            }
            stack.push(i);
        }
        return res;
    }
}
