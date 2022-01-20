package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (二分查找)
 * https://leetcode-cn.com/problems/binary-search/
 * @date 2021/9/6 9:55
 */
public class _704二分查找 {

    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            //可以防止left+right溢出(超出整数范围)
            int mid = left + (right-left)/2;
            // int mid = (left + right)/2;
            if(nums[mid] > target){
                right = mid - 1;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else{
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] sum = {2,5};
        System.out.println(search(sum, 5));
    }
}
