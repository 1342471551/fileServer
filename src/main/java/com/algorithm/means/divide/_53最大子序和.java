package com.algorithm.means.divide;

/**
 * @author wangyj
 * @Description: (最大子序和) 分治解决
 * https://leetcode-cn.com/problems/maximum-subarray/
 * @date 2021/6/4 17:05
 */
public class _53最大子序和 {

    //暴力 O(n^2)
    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        for (int begin = 0; begin < nums.length; begin++) {
            int sum = 0;
            for (int end = begin; end < nums.length; end++) {
                sum += nums[end];
                max = Math.max(sum, max);
            }
        }
        return max;
    }

    //分治 空间:O(logn) 时间:O(nlogn)
    public static int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return maxSubArray1(nums, 0, nums.length);
    }

    //求解begin-end最大连续子序列和
    public static int maxSubArray1(int[] nums, int begin, int end) {
        if (end - begin < 2) return nums[begin];
        int mid = (begin + end) >> 1;
        int leftMid = 0, leftMidMax = Integer.MIN_VALUE;

        //计算从mid出发左右相加最大序列和 其中begin,end左闭右开
        for (int i = mid - 1; i >= begin; i--) {
            leftMid += nums[i];
            leftMidMax = Math.max(leftMid, leftMidMax);
        }
        int rightMid = 0, rightMidMax = Integer.MIN_VALUE;
        for (int j = mid; j < end; j++) {
            rightMid += nums[j];
            rightMidMax = Math.max(rightMid, rightMidMax);
        }

        int leftMax = maxSubArray1(nums, begin, mid);
        int rightMax = maxSubArray1(nums, mid, end);
        //序列左边或右边中最大序列之和
        int max = Math.max(leftMax, rightMax);

        //返回mid左,mid右,从mid开始左边加右边三个里面最大的一个序列和
        return Math.max(max, (leftMidMax + rightMidMax));
    }

    public static void main(String[] args) {
        int[] nums = {-2, -1};
        System.out.println(maxSubArray(nums));
    }
}
