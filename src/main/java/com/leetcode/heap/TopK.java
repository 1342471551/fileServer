package com.leetcode.heap;

import com.algorithm.structure.heap.BinaryHeap;
import com.algorithm.structure.heap.Heap;

/**
 * @author wangyj
 * @Description: (Top k问题)
 * @date 2021/4/23 14:30
 */
public class TopK {

    //找出数组元素中最大的top个元素 O(nlogk)
    public Heap<Integer> TopK(int[] elements, int top) {
        Heap<Integer> heap = new BinaryHeap<>();
        //使用小顶堆,每次与堆顶比较若小则替换
        for (int i = 0; i < elements.length; i++) {
            if (heap.size() < 5) {
                heap.add(elements[i]);
            } else {
                if (elements[i] > heap.get()) {
                    heap.replace(elements[i]);
                }
            }
        }
        return heap;
    }
}
