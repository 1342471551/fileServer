package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (目标和)
 * https://leetcode-cn.com/problems/target-sum/
 * @date 2021/6/8 16:20
 */
public class _494目标和 {

    int count = 0;

    public int findTargetSumWays(int[] nums, int target) {
        backtrack(nums, target, 0, 0);
        return count;
    }


    public void backtrack(int[] nums, int target, int index, int sum) {
        if (index == nums.length) {
            if (sum == target) count++;
            return;
        }
        backtrack(nums, target, index + 1, sum + nums[index]);
        backtrack(nums, target, index + 1, sum - nums[index]);
    }

}
