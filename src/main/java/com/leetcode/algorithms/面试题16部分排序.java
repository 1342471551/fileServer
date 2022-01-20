package com.leetcode.algorithms;

/**
 * @author wangyj
 * @Description: (部分排序)
 * https://leetcode-cn.com/problems/sub-sort-lcci/
 * @date 2021/7/8 17:46
 */
public class 面试题16部分排序 {

    //从左扫描第一个逆序的 从右扫描第一个逆序的
    public static int[] subSort(int[] array) {
        if (array == null || array.length < 1) return new int[]{-1, -1};
        int l = -1, r = -1;
        int max = array[0], min = array[array.length - 1];
        for (int i = 1; i < array.length; i++) {
            if (array[i] >= max) {
                max = array[i];
            } else {
                r = i;
            }
        }
        for (int j = array.length - 2; j >= 0; j--) {
            if (array[j] <= min) {
                min = array[j];
            } else {
                l = j;
            }
        }
        return new int[]{l, r};
    }
}
