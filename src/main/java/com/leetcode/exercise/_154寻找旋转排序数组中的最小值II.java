package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (寻找旋转排序数组中的最小值II)
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/
 * @date 2021/4/9 13:24
 */
public class _154寻找旋转排序数组中的最小值II {
    public static int findMin(int[] nums) {
        int pre = 0, rear = nums.length - 1, min = 0;
        while (pre <= rear) {
            min = pre + ((rear - pre) >> 1);
            //由于数组向右旋转移动,只能和末尾比较！
            if (nums[min] < nums[rear]) {
                rear = min;
            } else if (nums[min] == nums[rear]) {
                //若相等则最后一位前移一下
                rear--;
            } else {
                pre = min + 1;
            }
        }
        return nums[min];
    }


    public static void main(String[] args) {
        int[] sums = {3, 1, 3};
        int min1 = findMin(sums);
        System.out.println(min1);
    }
}
