package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (山峰数组的顶部)
 * https://leetcode-cn.com/problems/B1IidL/
 * @date 2021/10/14 11:26
 */
public class 剑指OfferII069山峰数组的顶部 {


    public int peakIndexInMountainArray(int[] arr) {
        int right = arr.length - 2;
        int left = 1;
        int res = 0;
        while (left <= right) {
            //防止int类型溢出
            int mid = left + (right - left) / 2;
            if (arr[mid] > arr[mid + 1]) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    public int peakIndexInMountainArray1(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return i;
            }
        }
        return 0;
    }
}
