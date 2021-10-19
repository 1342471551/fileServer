package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (删除有序数组中的重复项II)
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/
 * @date 2021/4/6 16:03
 */
public class _80删除有序数组中的重复项II {
    public static int removeDuplicates(int[] nums) {
        if (nums.length <= 2) return nums.length;
        int index = 2;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] != nums[index - 2]) {
                nums[index++] = nums[i];
            }
        }
        return index;
    }

    public static int removeDuplicates1(int[] nums) {
        int i = 0;
        for (int n : nums) {
            if (i < 2 || n > nums[i - 2]) {
                nums[i++] = n;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        removeDuplicates(nums);
        removeDuplicates1(nums);
    }


}
