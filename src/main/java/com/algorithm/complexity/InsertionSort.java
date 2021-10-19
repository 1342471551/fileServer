package com.algorithm.complexity;

/**
 * @author wangyj
 * @Description: (插入排序)
 * 1.执行过程中分两部分,前面是已排序的 后面是待排序的
 * 2.从头到尾依次找出待排序元素插入合适位置
 * @date 2021/4/28 9:19
 */
public class InsertionSort extends Sort {

    protected void sort1() {
        for (int i = 1; i < array.length; i++) {
            int index = i;
            //当前位置小于已比较位置则进行循环元素交换比较
            while (index > 0 && cmp(index, index - 1) < 0) {
                swap(index, index - 1);
                index--;
            }
        }
    }

    protected void sort2() {
        for (int i = 1; i < array.length; i++) {
            int index = i;
            Integer integer = array[index];
            //当比较元素与排序元素相比较小时向后挪动元素
            while (index > 0 && (integer - array[index - 1]) < 0) {
                array[index] = array[index - 1];
                index--;
            }
            array[index] = integer;
        }
    }

    @Override
    protected void sort() {
        for (int i = 1; i < array.length; i++) {
            int index = i;
            Integer integer = array[index];
            int search = search(index);
            for (int i1 = index; i1 > search; i1--) {
                array[i1] = array[i1 - 1];
            }
            array[search] = integer;
        }
    }

    //通过二分搜索找到插入位置[0,index)范围
    private int search(int index) {
        //待插入元素
        int v = array[index];
        int begin = 0;
        int end = index;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (v < array[mid]) {
                end = mid;
            } else {
                begin = mid + 1;
            }
        }
        return begin;
    }

    public static void main(String[] args) {
        InsertionSort insertionSort = new InsertionSort();
        insertionSort.testSort();
    }
}
