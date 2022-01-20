package com.leetcode.algorithms;

import java.util.HashMap;

/**
 * @author wangyj
 * @Description: (两数之和)
 * https://leetcode-cn.com/problems/two-sum/
 * @date 2021/7/5 10:01
 */
public class _1两数之和 {

    public int[] twoSum(int[] nums, int target) {
        if (nums == null) return null;
        //key:数值 value:下标
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer idx = hashMap.get(target - nums[i]);
            if (idx != null) return new int[]{idx, i};
            hashMap.put(nums[i], i);
        }
        return null;
    }
}
