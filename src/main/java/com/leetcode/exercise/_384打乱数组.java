package com.leetcode.exercise;

import java.util.Random;

/**
 * @author wangyj
 * @Description: (打乱数组)
 * https://leetcode-cn.com/problems/shuffle-an-array/
 * @date 2021/11/22 4:56 下午
 */
public class _384打乱数组 {

    int[] nums;
    int[] original;
    int len;

    public _384打乱数组(int[] nums) {
        len = nums.length;
        this.nums = nums;
        this.original = new int[len];
        System.arraycopy(nums, 0, original, 0, len);
    }

    public int[] reset() {
        System.arraycopy(original, 0, nums, 0, len);
        return nums;
    }

    public int[] shuffle() {
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            int j = i + random.nextInt(len - i);
            int temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;
        }
        return nums;
    }
}
