package com.leetcode.algorithms;

/**
 * @author wangyj
 * @Description: (移动零)
 * https://leetcode-cn.com/problems/move-zeroes/
 * @date 2021/7/5 9:29
 */
public class _283移动零 {

    public void moveZeroes(int[] nums) {
        if (nums == null) return;
        int cur = 0;
        for (int num : nums) {
            if (num == 0) continue;
            nums[cur] = num;
            cur++;
        }
        for (int i = cur; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
