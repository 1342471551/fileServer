package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (在排序数组中查找数字)
 * https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/
 * @date 2021/7/16 9:41
 */
public class 剑指Offer53在排序数组中查找数字 {

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        int i = 0;
        int res = 0;
        while (i < nums.length && nums[i] <= target) {
            if (nums[i] == target) res++;
            i++;
        }
        return res;
    }

    public int search1(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int count = 0;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] >= target)
                right = mid;
            if (nums[mid] < target)
                left = mid + 1;
        }
        while (left < nums.length && nums[left++] == target)
            count++;
        return count;
    }
}
