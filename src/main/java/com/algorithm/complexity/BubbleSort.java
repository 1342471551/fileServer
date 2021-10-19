package com.algorithm.complexity;

/**
 * @author wangyj
 * @Description: (冒泡排序)
 * @date 2021/4/27 9:06
 */
public class BubbleSort extends Sort {
    @Override
    protected void sort() {
        for (int i = array.length - 1; i > 0; i--) {
            //若这一轮循环都没有交换,则说明数组是顺序排序的可以提前结束
            boolean sort = true;
            //初始值在数组完全有序的情况下有用
            int sortIndex = 1;
            for (int j = 1; j <= i; j++) {
                if (cmp(j, j - 1) < 0) {
                    swap(array[j - 1], array[j]);
                    sort = false;
                    sortIndex = j;
                }
            }
            if (sort) break;
            //若后面数据有序则可以提前确定下次外循环开始位置
            i = sortIndex;
        }
    }
}
