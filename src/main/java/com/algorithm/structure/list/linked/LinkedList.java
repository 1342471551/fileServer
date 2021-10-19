package com.algorithm.structure.list.linked;

import com.algorithm.structure.list.AbstractList;

/**
 * @author wangyj
 * @Description: (双向链表)
 * @date 2021/3/29 11:23
 */
public class LinkedList<E> extends AbstractList<E> {

    //链表首节点
    private Node<E> firstNode;
    //链表尾节点
    private Node<E> lastNode;

    public E get(int index) {
        return node(index).element;
    }

    public E set(int index, E element) {
        Node<E> node = node(index);
        E old = node.element;
        node.element = element;
        return old;
    }

    public int indexOf(E element) {
        Node<E> node = firstNode;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (node.element == null) return i;
                node = node.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) return i;
                node = node.next;
            }
        }
        return DEFAULT_NOT_FOUND;
    }


    public void clear() {
        size = 0;
        firstNode = null;
        lastNode = null;
    }


    public void add(int index, E element) {
        rangeCheckForAdd(index);

        if (index == size) {
            lastNode = new Node<E>(element, lastNode, null);
            if (lastNode.prev == null) {
                //表示这是双向链表的第一个元素
                firstNode = lastNode;
            } else {
                lastNode.prev.next = lastNode;
            }
        } else {
            //获取此位置元素节点
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> newNode = new Node<E>(element, prev, next);
            next.prev = newNode;

            if (prev == null) {
                //表示index为0,前一个节点为空
                firstNode = newNode;
            } else {
                prev.next = newNode;
            }
        }
        size++;
    }

    public E remove(int index) {
        rangeCheck(index);

        Node<E> node = node(index);
        //要删除元素的上一个和下一个
        Node<E> prev = node.prev;
        Node<E> next = node.next;
        //判断删除是否为头节点 index==0
        if (prev == null) {
            firstNode = next;
        } else {
            prev.next = next;
        }
        //判断删除是否为尾节点 index==size-1
        if (next == null) {
            lastNode = prev;
        } else {
            next.prev = prev;
        }
        size--;
        return node.element;
    }

    //获取index对应node节点对象
    private Node<E> node(int index) {
        rangeCheck(index);

        if (index < (size >> 1)) {
            Node<E> node = firstNode;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {
            Node<E> node = lastNode;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }
    }

    //当打印一个类会默认调用这类的toString方法
    @Override
    public String toString() {
        Node<E> node = firstNode;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("size=").append(size).append(",[");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(node);
            node = node.next;
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    //私有静态内部类
    private static class Node<E> {
        //此节点元素值
        E element;
        //指向上一个节点的指针
        Node<E> prev;
        //指向下一个节点的指针
        Node<E> next;

        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            if (prev != null) {
                stringBuilder.append(prev.element);
            } else {
                stringBuilder.append("null");
            }
            stringBuilder.append("_").append(element).append("_");
            if (next != null) {
                stringBuilder.append(next.element);
            } else {
                stringBuilder.append("null");
            }
            return stringBuilder.toString();
        }
    }
}
