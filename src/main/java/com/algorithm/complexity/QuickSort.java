package com.algorithm.complexity;

/**
 * @author wangyj
 * @Description: (快速排序)
 * @date 2021/4/28 16:41
 */
public class QuickSort extends Sort {

    @Override
    protected void sort() {
        quick(0, array.length - 1);
    }

    private void quick(int begin, int end) {
        if (begin < end) {
            int sort = sort(begin, end);
            quick(begin, sort - 1);
            quick(sort + 1, end);
        }
    }


    private int sort(int begin, int end) {
        Integer tmp = array[begin];
        while (begin < end) {
            while (begin < end && array[end] >= tmp) {
                end--;
            }
            array[begin] = array[end];
            while (begin < end && array[begin] <= tmp) {
                begin++;
            }
            array[end] = array[begin];
        }
        array[begin] = tmp;
        return begin;
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        quickSort.testSort();
    }
}
