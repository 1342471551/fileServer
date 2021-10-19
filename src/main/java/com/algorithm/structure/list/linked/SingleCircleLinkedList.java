package com.algorithm.structure.list.linked;

import com.algorithm.structure.list.AbstractList;

/**
 * @author wangyj
 * @Description: (单向循环链表)
 * @date 2021/3/29 11:23
 */
public class SingleCircleLinkedList<E> extends AbstractList<E> {

    //链表首节点
    private Node<E> firstNode;

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
    }


    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if (index == 0) {
            //首结点,先指针指向firstNode节点,之后再把本身地址传给firstNode
            //先查找不要急着插入首节点,防止添加之后多了一个元素查找尾节点Node有误！
            Node<E> newFirstNode = new Node<E>(element, firstNode);
            //拿到最后位置节点,若没有节点则拿到要插入节点本身
            Node<E> last = (size == 0) ? newFirstNode : node(size - 1);
            firstNode = newFirstNode;
            last.next = firstNode;
        } else {
            Node<E> prev = node(index - 1);
            prev.next = new Node<E>(element, prev.next);
        }
        size++;
    }

    public E remove(int index) {
        rangeCheck(index);
        //假设默认为首届点,当index==0则不用改变了直接返回
        Node<E> node = firstNode;
        if (index == 0) {
            //判断只有一个元素的删除
            if (size == 1) {
                firstNode = null;
            } else {
                Node<E> last = node(size - 1);
                firstNode = firstNode.next;
                last.next = firstNode;
            }
        } else {
            Node<E> prev = node(index - 1);
            node = prev.next;
            prev.next = prev.next.next;
        }
        size--;
        return node.element;
    }

    //获取index对应node节点对象
    private Node<E> node(int index) {
        rangeCheck(index);
        Node<E> node = firstNode;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public String toString() {
        Node<E> node = firstNode;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("size=").append(size).append(",[");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(node.element);
            node = node.next;
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    //私有静态内部类
    private static class Node<E> {
        //此节点元素值
        E element;
        //指向下一个节点的指针
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }
}
