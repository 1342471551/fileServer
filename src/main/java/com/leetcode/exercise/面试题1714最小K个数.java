package com.leetcode.exercise;

import java.util.Arrays;

/**
 * @author wangyj
 * @Description: (最小K个数)
 * https://leetcode-cn.com/problems/smallest-k-lcci/
 * @date 2021/9/3 9:53
 */
public class 面试题1714最小K个数 {


    public int[] smallestK(int[] arr, int k) {
        int[] res = new int[k];
        Arrays.sort(arr);
        System.arraycopy(arr, 0, res, 0, k);
        return res;
    }

    public int[] smallestK1(int[] arr, int k) {
        quick(arr, 0, arr.length - 1);
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = arr[i];
        }
        return res;
    }


    private void quick(int[] arr, int begin, int end) {
        if (begin < end) {
            int sort = sort(arr, begin, end);
            quick(arr, begin, sort - 1);
            quick(arr, sort + 1, end);
        }
    }

    private int sort(int[] arr, int begin, int end) {
        int temp = arr[begin];
        while (begin < end) {
            while (begin < end && arr[end] >= temp) {
                end--;
            }
            arr[begin] = arr[end];
            while (begin < end && arr[begin] <= temp) {
                begin++;
            }
            arr[end] = arr[begin];
        }
        arr[begin] = temp;
        return temp;
    }
}
