package com.algorithm.structure.queue;

import org.omg.CORBA.Object;

/**
 * @author wangyj
 * @Description: (使用数组实现单向循环队列)
 * @date 2021/4/1 11:54
 */
public class CircleQueue<E> {
    //存放队头元素下标
    private int front;
    private int size;
    private E[] elements;

    //定义默认数组大小
    private static final int DEFAULT_CAPACITY = 10;

    public CircleQueue() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[index(i)] = null;
        }
        size = 0;
        front = 0;
    }

    //队尾添加元素
    public void enQueue(E element) {
        //判断容量是否扩容
        ensureCapacity(size + 1);
        //由于多了队头指针,添加元素需要指针加上元素数量对数组取模
        elements[index(size)] = element;
        size++;
    }

    //队头删除元素
    public E deQueue() {
        E element = elements[front];
        elements[front] = null;
        front = index(1);
        size--;
        return element;
    }

    public E front() {
        return elements[front];
    }

    //根据索引获取加上头节点的真实索引
    private int index(int index) {
//        return (index + front) % elements.length;
        //循环队列不会超过数组长度的两倍 用加减效率高于取模运算！
        index += front;
        return index - (index >= elements.length ? elements.length : 0);
    }


    //保证capacity容量,扩容 申请新空间,拷贝元素到新空间
    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= size) return;
        int newCapacity = oldCapacity + oldCapacity >> 1;
        E[] newElements = (E[]) new java.lang.Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[index(i)];
        }
        elements = newElements;
        //重置队头指针
        front = 0;
    }
}
