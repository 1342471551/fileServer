package com.leetcode.exercise;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author wangyj
 * @Description: (搜索旋转排序数组II)
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/
 * @date 2021/4/7 18:09
 */
public class _81搜索旋转排序数组II {


    public boolean search1(int[] nums, int target) {
        return Arrays.stream(nums).boxed().collect(Collectors.toList()).contains(target);
    }

    //这个只是娱乐
    public boolean search(int[] nums, int target) {
        for (int num : nums) {
            if (num == target) return true;
        }
        return false;
    }
}
