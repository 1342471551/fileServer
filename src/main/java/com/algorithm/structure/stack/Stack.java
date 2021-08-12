package com.algorithm.structure.stack;

import com.algorithm.structure.list.List;
import com.algorithm.structure.list.array.ArrayList;

/**
 * @author wangyj
 * @Description: (使用arraylist编写栈)
 * @date 2021/3/31 20:10
 */
public class Stack<E> {

    /**
     * java.util.stack使用动态数组实现
     * 使用vector相比arraylist是线程安全的数组方法使用syn加锁
     * 应用:利用双栈(当第一个栈新增则清空第二个栈)实现浏览器前进后退,撤销恢复等功能
     */

    //内部使用arrayList或者linkedList都可
    List<E> list = new ArrayList<E>();
//    List<E> list=new LinkedList<E>();

    public void clear() {
        list.clear();
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void push(E element) {
        list.add(element);
    }

    public E pop() {
        return list.remove(list.size() - 1);
    }

    //获取栈顶元素
    public E peek() {
        return list.get(list.size() - 1);
    }
}
