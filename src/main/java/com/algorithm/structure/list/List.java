package com.algorithm.structure.list;

/**
 * @author wangyj
 * @Description: (list接口)
 * @date 2021/3/29 11:24
 */
public interface List<E> {
    //定义未找到元素返回的静态常量
    int DEFAULT_NOT_FOUND = -1;

    //判断大小
    int size();

    //判断空
    boolean isEmpty();

    //获取具体元素
    E get(int index);

    //设置具体位置元素,并且返回老的元素
    E set(int index, E element);

    //查看元素索引
    int indexOf(E element);

    //查看是否包含此元素
    boolean contains(E element);

    //清除所有元素
    void clear();

    //添加元素
    void add(E element);

    //指定位置添加元素
    void add(int index, E element);

    //删除元素
    E remove(int index);
}
