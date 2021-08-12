package com.algorithm.complexity;

/**
 * @author wangyj
 * @Description: (选择排序)
 * 找到元素中最大的元素和最后元素进行替换,循环此操作
 * @date 2021/4/27 9:53
 */
public class SelectionSort extends Sort {
    @Override
    protected void sort() {
        for (int i = array.length - 1; i > 0; i--) {
            int max = 0;
            for (int j = 1; j <= i; j++) {
                if (cmp(j, j - 1) > 0) {
                    max = j;
                }
            }
            swap(array[max], array[i]);
        }
    }
}
