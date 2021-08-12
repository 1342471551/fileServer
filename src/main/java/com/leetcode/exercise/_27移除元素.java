package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (移除元素)
 * https://leetcode-cn.com/problems/remove-element/
 * @date 2021/4/19 19:16
 */
public class _27移除元素 {
    public static int removeElement(int[] nums, int val) {
        if (nums == null) return 0;
        int length = nums.length - 1;
        for (int i = 0; i <= length; i++) {
            while (nums[i] == val && i <= length) {
                nums[i] = nums[length];
                length--;
                if (length < 0) return 0;
            }
        }
        return length + 1;
    }

    //别人的方法 我是fw
    public int removeElement1(int[] nums, int val) {
        int index = 0;
        for (int i = 0; i < nums.length; i++)
            if (nums[i] != val)
                nums[index++] = nums[i];
        return index;
    }

    public static void main(String[] args) {
        int[] nums = {3};
        removeElement(nums, 3);
    }
}
