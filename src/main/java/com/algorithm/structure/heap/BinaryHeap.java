package com.algorithm.structure.heap;

import com.algorithm.structure.tree.Comparator;

/**
 * @author wangyj
 * @Description: (完全二叉堆)
 * @date 2021/4/22 19:45
 */
public class BinaryHeap<E> extends AbstractHeap<E> implements Heap<E> {

    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap(E[] elements, Comparator<E> comparator) {
        super(comparator);
        if (elements == null || elements.length == 0) {
            this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        } else {
            size = elements.length;
            int capacity = Math.max(elements.length, DEFAULT_CAPACITY);
            this.elements = (E[]) new Object[capacity];
            for (int i = 0; i < elements.length; i++) {
                this.elements[i] = elements[i];
            }
            //进行快速建堆
            heapify();
        }
    }


    public BinaryHeap(E[] elements) {
        this(elements, null);
    }

    public BinaryHeap(Comparator<E> comparator) {
        super(comparator);
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public BinaryHeap() {
    }


    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public void add(E element) {
        //检查元素是否为空
        elementNotNullCheck(element);
        //判断是否需要扩容
        ensureCapacity(size + 1);
        //添加元素
        elements[size++] = element;
        //进行元素上滤
        siftUp(size - 1);
    }

    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    @Override
    public E remove() {
        emptyCheck();
        int lastIndex = --size;
        E root = elements[0];
        elements[0] = elements[lastIndex];
        elements[lastIndex] = null;
        //进行元素下滤
        siftDown(0);
        return root;
    }

    //删除堆顶并添加新元素
    @Override
    public E replace(E element) {
        elementNotNullCheck(element);
        E root = null;
        if (size == 0) {
            elements[0] = element;
            size++;
        } else {
            root = elements[0];
            elements[0] = element;
            siftDown(0);
        }
        return root;
    }

    //进行批量建堆
    private void heapify() {
        //使用自下而上的下滤 O(n)
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
        //使用自上而下的上滤 O(nlogn)
//        for (int i = 1; i < elements.length; i++) {
//            siftUp(i);
//        }
    }

    //保证capacity容量,扩容 申请新空间,拷贝元素到新空间
    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= size) return;
        int newCapacity = oldCapacity + oldCapacity >> 1;
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    //进行元素上滤
    private void siftUp(int index) {
        E e = elements[index];
        while (index > 0) {
            int parentIndex = (index - 1) >> 1;
            E p = elements[parentIndex];
            if (compare(e, p) <= 0) break;
            //父元素存储index位置
            elements[index] = p;
            //获取父节点索引,当作index进行下次比较
            index = parentIndex;
        }
        //最后不满足条件时,再把插入元素赋值
        elements[index] = e;
    }

    //进行元素下滤操作
    private void siftDown(int index) {
        int half = size >> 1;
        E e = elements[index];
        //保证index小于非叶子节点数量
        while (index < half) {
            //有可能有左子节点,或者左右子节点(默认左节点)
            int childIndex = (index << 1) + 1;
            E child = elements[childIndex];
            //判读右节点
            int rightIndex = (index << 1) + 2;
            if (rightIndex < size && compare(elements[rightIndex], child) > 0) {
                child = elements[childIndex = rightIndex];
            }
            if (compare(e, child) >= 0) break;
            elements[index] = child;
            index = childIndex;
        }
        elements[index] = e;
    }


    private void emptyCheck() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IndexOutOfBoundsException("element is not null");
        }
    }
}
