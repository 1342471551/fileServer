package com.algorithm.structure.list.linked;

import com.algorithm.structure.list.AbstractList;

/**
 * @author wangyj
 * @Description: (双向循环链表)
 * @date 2021/3/29 11:23
 */
public class CircleLinkedList<E> extends AbstractList<E> {

    //链表首节点
    private Node<E> firstNode;
    //链表尾节点
    private Node<E> lastNode;

    //新增指针指向节点元素可以改变
    private Node<E> current;

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
            lastNode = new Node<E>(element, lastNode, firstNode);
            if (lastNode.prev == null) {
                //表示这是双向链表的第一个元素,并且自己上一个下一个都指向自己
                firstNode = lastNode;
                firstNode.next = firstNode;
                firstNode.prev = firstNode;
            } else {
                lastNode.prev.next = lastNode;
                //头节点prev指向新添加的尾节点
                firstNode.prev = lastNode;
            }
        } else {
            //获取此位置元素节点(新添加元素的下一个节点)
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> newNode = new Node<E>(element, prev, next);
            next.prev = newNode;
            prev.next = newNode;
            if (index == 0) {
                //表示index为0,firstNode指针指向新添加节点
                firstNode = newNode;
            }
        }
        size++;
    }

    public E remove(int index) {
        rangeCheck(index);
        return remove(node(index));
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

    //重置指针指向头节点
    public void reset() {
        current = firstNode;
    }

    //让指针向后走一步
    public E next() {
        if (current == null) return null;
        current = current.next;
        return current.element;
    }

    //删除current位置节点,并指向下一个节点
    public E remove() {
        if (current == null) return null;
        Node<E> next = current.next;
        E element = remove(current);
        if (size == 0) {
            current = null;
        } else {
            current = next;
        }
        return element;

    }

    private E remove(Node<E> node) {
        //判断是否只有一个节点
        if (size == 1) {
            firstNode = null;
            lastNode = null;
        } else {
            //要删除元素的上一个和下一个
            Node<E> prev = node.prev;
            Node<E> next = node.next;
            //双向循环列表首尾都不可能为空直接执行
            prev.next = next;
            next.prev = prev;
            //判断删除是否为头节点 index==0
            if (node == firstNode) {
                firstNode = next;
            }
            //判断删除是否为尾节点 index==size-1
            if (node == lastNode) {
                lastNode = prev;
            }
        }
        size--;
        return node.element;
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

    public Node<E> getCurrent() {
        return current;
    }
}
