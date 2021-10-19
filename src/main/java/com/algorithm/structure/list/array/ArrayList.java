package com.algorithm.structure.list.array;

import com.algorithm.structure.list.AbstractList;

/**
 * @author wangyj
 * @Description: (自定义动态数组arrayList)
 * @date 2021/3/25 9:11
 */
public class ArrayList<E> extends AbstractList<E> {

    /**
     * 可优化方案:
     * 可以添加一个指针,对数组长度取模获取对应节点元素
     * 首部添加/删除元素不移动数组,改变指针位置(指向首届点,可添加到尾部取模决定顺序)
     * 中间添加元素看向前还是向后挪动元素最优
     * 当扩容或缩容再进行重新排版!!!
     */

    //存放元素到数组
    private E[] elements;

    //定义默认数组大小
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayList(int capaticy) {
        capaticy = capaticy < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : capaticy;
        elements = (E[]) new Object[capaticy];
    }

    public ArrayList() {
        //使用this直接调用有参构造函数
        this(DEFAULT_CAPACITY);
    }


    //获取具体元素
    public E get(int index) {
        rangeCheck(index);

        //根据数组地址加上(索引*字节)算出内存地址直接访问 O(1)
        return elements[index];
    }

    //设置具体位置元素,并且返回老的元素
    public E set(int index, E element) {
        rangeCheck(index);
        E old = elements[index];
        elements[index] = element;
        return old;
    }

    //查看元素索引
    public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) return i;
            }
        }
        return DEFAULT_NOT_FOUND;
    }


    //清除所有元素
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
        //恢复默认数组长度
        if (elements != null && elements.length > DEFAULT_CAPACITY) {
            elements = (E[]) new Object[DEFAULT_CAPACITY];
        }
    }


    //指定位置添加元素
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        ensureCapacity(size + 1);
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }

    //删除元素
    public E remove(int index) {
        rangeCheck(index);
        E old = elements[index];
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
        elements[--size] = null;
        //判断是否缩容
        trim();
        return old;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("size=").append(size).append(",[");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(elements[i]);
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
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

    //进行数组是否缩容
    private void trim() {
        int capacity = elements.length;
        int newCapacity = capacity >> 1;
        if (size >= (capacity >> 1) || capacity <= DEFAULT_CAPACITY) return;
        //缩容
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

}
