package com.algorithm.structure.heap;

/**
 * @author wangyj
 * @Description: (堆接口)
 * @date 2021/4/22 19:41
 */
public interface Heap<E> {
    //元素数量
    int size();

    //是否为空
    boolean isEmpty();

    //清空
    void clear();

    //添加
    void add(E element);

    //获取堆顶元素
    E get();

    //删除堆顶元素
    E remove();

    //删除堆顶元素并插入一个新元素
    E replace(E element);
}
