package com.algorithm.structure.queue;

import com.algorithm.structure.list.linked.LinkedList;

/**
 * @author wangyj
 * @Description: (使用双向链表实现队列)
 * @date 2021/4/1 10:08
 */
public class Queue<E> {

    /**
     * 由于头尾插入删除频繁,优先使用双向链表实现 O(1)
     */

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
    public void enQueue(E element) {
        list.add(element);
    }

    //队头删除元素
    public E deQueue() {
        return list.remove(0);
    }

    public E front() {
        return list.get(0);
    }

}
