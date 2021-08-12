package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (合并两个有序数组)
 * https://leetcode-cn.com/problems/merge-sorted-array/
 * @date 2021/4/7 20:48
 */
public class _88合并两个有序数组 {

    //自己相的 先添加 数组快排
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums2 == null) return;
        for (int i = m; i < m + n; i++) {
            nums1[i] = nums2[i - m];
        }
        quickMethod(nums1, 0, m + n - 1);
    }

    public static void quickMethod(int[] arr, int low, int high) {
        if (low < high) {
            int index = indexOfArr(arr, low, high);
            quickMethod(arr, low, index - 1);
            quickMethod(arr, index + 1, high);
        }
    }

    public static int indexOfArr(int[] arr, int low, int high) {
        int temp = arr[low];
        while (low < high) {
            while (low < high && arr[high] >= temp) {
                high--;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] <= temp) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = temp;
        return low;
    }

    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        int p = m-- + n-- - 1;
        while (m >= 0 && n >= 0) {
            nums1[p--] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
        }

        while (n >= 0) {
            nums1[p--] = nums2[n--];
        }
    }


}
