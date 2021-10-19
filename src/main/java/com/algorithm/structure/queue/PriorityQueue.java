package com.algorithm.structure.queue;

import com.algorithm.structure.heap.BinaryHeap;
import com.algorithm.structure.tree.Comparator;

/**
 * @author wangyj
 * @Description: (优先级队列)
 * @date 2021/4/25 10:14
 */
public class PriorityQueue<E> {

    private BinaryHeap<E> heap;

    public PriorityQueue(Comparator<E> comparator) {
        heap = new BinaryHeap<>(comparator);
    }

    public PriorityQueue() {
        this(null);
    }

    public void clear() {
        heap.clear();
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    //队尾添加元素
    public void enQueue(E element) {
        heap.add(element);
    }

    //队头删除元素
    public E deQueue() {
        return heap.remove();
    }

    public E front() {
        return heap.get();
    }
}
