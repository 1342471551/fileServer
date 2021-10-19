package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (找到需要补充粉笔的学生编号)
 * https://leetcode-cn.com/problems/find-the-student-that-will-replace-the-chalk/
 * @date 2021/9/10 10:52
 */
public class _1894找到需要补充粉笔的学生编号 {

    public int chalkReplacer(int[] chalk, int k) {
        int n = chalk.length;
        long sum = 0;
        for (int cha : chalk) {
            sum += cha;
        }
        k %= sum;
        for (int i = 0; i < n; i++) {
            if (chalk[i] > k) {
                return i;
            }
            k -= chalk[i];
        }
        return -1;
    }
}
