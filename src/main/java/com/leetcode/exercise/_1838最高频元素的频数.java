package com.leetcode.exercise;

import java.util.Arrays;

/**
 * @author wangyj
 * @Description: (最高频元素的频数)
 * https://leetcode-cn.com/problems/frequency-of-the-most-frequent-element/
 * @date 2021/7/19 8:55
 */
public class _1838最高频元素的频数 {

    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int max = 0, tempSum = 0;
        for (int i = 0, j = 0; j < nums.length; j++) {
            while (nums[j] * (j - i) - tempSum > k) {
                tempSum -= nums[i++];
            }
            tempSum += nums[j];
            max = Math.max(max, j - i + 1);
        }
        return max;
    }
}
