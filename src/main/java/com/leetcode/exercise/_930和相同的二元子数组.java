package com.leetcode.exercise;

import java.util.Map;

/**
 * @author wangyj
 * @Description: (和相同的二元子数组)
 * https://leetcode-cn.com/problems/binary-subarrays-with-sum/
 * @date 2021/7/8 17:48
 */
public class _930和相同的二元子数组 {

    public int numSubarraysWithSum(int[] nums, int goal) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == goal) {
                result += 1;
            }
            int temp = nums[i];
            for(int j = i+1; j < nums.length; j++){
                if((temp += nums[j])== goal) {
                    result += 1;
                }
            }
        }
        return result;
    }
}
