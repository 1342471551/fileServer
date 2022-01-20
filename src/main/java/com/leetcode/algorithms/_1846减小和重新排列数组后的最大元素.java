package com.leetcode.algorithms;

import java.util.Arrays;

/**
 * @author wangyj
 * @Description: (减小和重新排列数组后的最大元素)
 * https://leetcode-cn.com/problems/maximum-element-after-decreasing-and-rearranging/
 * @date 2021/7/15 17:56
 */
public class _1846减小和重新排列数组后的最大元素 {

    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        arr[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] >= 1) {
                arr[i] = arr[i - 1] + 1;
            }
        }
        return arr[arr.length - 1];
    }
}
