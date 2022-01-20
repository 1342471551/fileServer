package com.leetcode.exercise;

import java.util.Arrays;

/**
 * @author wangyj
 * @Description: (丢失的数字)
 * https://leetcode-cn.com/problems/missing-number/
 * @date 2021/11/11 3:05 下午
 */
public class _268丢失的数字 {

    public int missingNumber(int[] nums) {
        int len = nums.length;
        nums = Arrays.stream(nums).sorted().toArray();
        for (int i = 0; i < len; i++) {
            if (nums[i]!=i) {
                return i;
            }
        }
        return len;
    }
}
