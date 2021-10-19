package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (寻找峰值)
 * https://leetcode-cn.com/problems/find-peak-element/
 * @date 2021/9/15 9:11
 */
public class _162寻找峰值 {

    /**
     * 规律一：如果nums[i] > nums[i+1]，则在i之前一定存在峰值元素
     * 规律二：如果nums[i] < nums[i+1]，则在i+1之后一定存在峰值元素
     */
    public int findPeakElement(int[] nums) {
        //右边进行减一操作,这样进行mid和mid+1比较
        int left = 0, right = nums.length - 1;
        for (; left < right; ) {
            //这样计算防止左右相加溢出
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

}
