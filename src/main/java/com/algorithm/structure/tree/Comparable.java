package com.algorithm.structure.tree;

/**
 * @author wangyj
 * @Description: (自定义比较方法,可以让E继承 让用户创建对象强制继承实现此类)
 * 缺点:对象比较方法固定,无法改变
 * @date 2021/4/2 14:28
 */
public interface Comparable<E> {
    int compare(E element);
}
