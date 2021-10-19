package com.algorithm.structure.queue;

/**
 * @author wangyj
 * @Description: (使用数组实现循环双端队列)
 * @date 2021/4/1 11:42
 */
public class CircleDeque<E> {

    private int front;
    private int size;
    private E[] elements;

    //定义默认数组大小
    private static final int DEFAULT_CAPACITY = 10;

    public CircleDeque() {
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
    public void enQueueRear(E element) {
        ensureCapacity(size + 1);
        elements[index(size)] = element;
        size++;
    }

    //队头添加元素
    public void enQueueFront(E element) {
        ensureCapacity(size + 1);
        front = index(-1);
        elements[front] = element;
        size++;
    }

    //队尾删除元素
    public E deQueueRear() {
        int rearIndex = index(size - 1);
        E element = elements[rearIndex];
        elements[rearIndex] = null;
        size--;
        return element;
    }

    //队头删除元素
    public E deQueueFront() {
        E element = elements[front];
        elements[front] = null;
        front = index(1);
        size--;
        return element;
    }

    public E front() {
        return elements[front];
    }

    public E rear() {
        return elements[index(size - 1)];
    }

    //根据索引获取加上头节点的真实索引
    private int index(int index) {
        index += front;
        //判断队头插入,但是首届点为0的情况
        if (index < 0) {
            return index + elements.length;
        }
        //循环队列不会超过数组长度的两倍 用加减效率高于取模运算！
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
