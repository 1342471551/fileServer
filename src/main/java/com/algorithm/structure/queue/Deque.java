package com.algorithm.structure.queue;

import com.algorithm.structure.list.linked.LinkedList;

/**
 * @author wangyj
 * @Description: (使用linkedList实现双端队列)
 * @date 2021/4/1 11:42
 */
public class Deque<E> {

    LinkedList<E> list = new LinkedList<E>();

    public void clear() {
        list.clear();
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    //队尾添加元素
    public void enQueueRear(E element) {
        list.add(element);
    }

    //队头添加元素
    public void enQueueFront(E element) {
        list.add(0, element);
    }

    //队尾删除元素
    public E deQueueRear() {
        return list.remove(list.size() - 1);
    }

    //队头删除元素
    public E deQueueFront() {
        return list.remove(0);
    }

    public E front() {
        return list.get(0);
    }

    public E rear() {
        return list.get(list.size() - 1);
    }
}
