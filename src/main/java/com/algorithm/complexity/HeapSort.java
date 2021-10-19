package com.algorithm.complexity;

import com.algorithm.structure.heap.BinaryHeap;

/**
 * @author wangyj
 * @Description: (堆排序)
 * 选择排序的优化,选择最大值放到最后 循环操作
 * @date 2021/4/27 10:11
 */
public class HeapSort extends Sort {

    @Override
    protected void sort() {
        //1.元素进行堆排序 2.选择堆顶元素放到末尾 3.进行堆元素size-- 4.进行堆顶下滤操作
        BinaryHeap<Integer> binaryHeap = new BinaryHeap<>(array);
        for (int i = array.length - 1; i > 0; i--) {
            Integer remove = binaryHeap.remove();
            array[i] = remove;
        }
    }

    public static void main(String[] args) {
        Sort sort=new HeapSort();
        sort.testSort();
    }
}
