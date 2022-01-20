package com.leetcode.exercise;

import java.util.Arrays;

/**
 * @author wangyj
 * @Description: (寻找旋转排序数组中的最小值)
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
 * @date 2021/4/8 14:53
 */
public class _153寻找旋转排序数组中的最小值 {

    public int findMin(int[] nums) {
        Arrays.sort(nums);
        return nums[0];
    }

    public static int findMin1(int[] nums) {
        int pre = 0, rear = nums.length - 1, mid = 0;
        while (pre <= rear) {
            mid = pre + ((rear - pre) >> 1);
            if (nums[mid] < nums[rear]) {
                rear = mid;
            } else {
                //mid肯定不是最小的直接+1跳过
                pre = mid + 1;
            }
        }
        System.gc();
        return nums[mid];
    }

    public static void main(String[] args) {
        int[] sums = {4, 5, 6, 0, 2, 3};
        int min1 = findMin1(sums);
        System.out.println(min1);
    }
}
