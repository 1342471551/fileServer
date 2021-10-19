package com.leetcode.algorithms;

/**
 * @author wangyj
 * @Description: (颜色分类)
 * https://leetcode-cn.com/problems/sort-colors/
 * @date 2021/7/8 17:02
 */
public class _75颜色分类 {


    //使用O(n)复杂完成此题 (O(1)一般都需要双向链表进行操作)
    //三指针进行操作 发现2与r指针位置交换r指针前移 发现0与l指针位置交换l指针前移 还有一个指针进行数据扫描(发现0的与l指针交换 r指针和扫描指针前移)
    public void sortColors(int[] nums) {
        int l = 0, r = nums.length - 1, i = 0;
        while (i <= r) {
            int temp = nums[i];
            if (temp == 0) {
                //与l指针交换值并且两个指针前移
                int var = nums[l];
                nums[l] = nums[i];
                nums[i] = var;
                i++;
                l++;
            } else if (temp == 1) {
                i++;
            } else {
                //与r指针交换值r指针前移i指针不变
                int var = nums[r];
                nums[r] = nums[i];
                nums[i] = var;
                r--;
            }
        }
    }


    public void sortColors1(int[] nums) {
        if (nums == null || nums.length < 1) return;
        quickSort(nums, 0, nums.length - 1);
    }

    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int i = indexOfArr(arr, low, high);
            quickSort(arr, low, i - 1);
            quickSort(arr, i + 1, high);
        }
    }

    private int indexOfArr(int[] arr, int low, int high) {
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
}
