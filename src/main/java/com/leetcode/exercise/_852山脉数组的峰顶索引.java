package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (山脉数组的峰顶索引)
 * https://leetcode-cn.com/problems/peak-index-in-a-mountain-array/
 * @date 2021/6/15 15:05
 */
public class _852山脉数组的峰顶索引 {

    public int peakIndexInMountainArray(int[] arr) {
        int i = 1;
        for (; i < arr.length; i++) {
            if (arr[i - 1] <= arr[i]) continue;
            break;
        }
        return i - 1;
    }

    //二分查找O(logn)
    public int peakIndexInMountainArray1(int[] arr) {
        int n = arr.length;
        int left = 1, right = n - 2, ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] > arr[mid + 1]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

}
