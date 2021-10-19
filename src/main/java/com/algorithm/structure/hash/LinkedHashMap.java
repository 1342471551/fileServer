package com.algorithm.structure.hash;

import java.util.Objects;

/**
 * @author wangyj
 * @Description: (维护插入顺序的哈希表)
 * @date 2021/4/22 9:49
 */
public class LinkedHashMap<K, V> extends HashMap<K, V> {
    //定义链表首届点,尾节点
    private LinkedNode<K, V> first;
    private LinkedNode<K, V> last;

    @Override
    protected MapNode<K, V> createNode(K key, V value, MapNode<K, V> parent) {
        //在添加的时候维护链表的顺序调用
        LinkedNode<K, V> LinkedNode = new LinkedNode<>(key, value, parent);
        if (first == null) {
            first = last = LinkedNode;
        } else {
            last.next = LinkedNode;
            LinkedNode.prev = last;
            last = LinkedNode;
        }
        return LinkedNode;
    }

    @Override
    public void clear() {
        super.clear();
        first = null;
        last = null;
    }

    @Override
    public boolean containsValue(V value) {
        LinkedNode<K, V> node = first;
        while (node != null) {
            if (Objects.equals(node.value, node)) return true;
            node = node.next;
        }
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (visitor == null) return;
        LinkedNode<K, V> node = first;
        while (node != null) {
            if (visitor.visit(node.key, node.value)) return;
            node = node.next;
        }
    }

    @Override
    protected void LinkedAfterRemove(MapNode<K, V> willNode, MapNode<K, V> node) {
        LinkedNode<K, V> linkedNode = (LinkedNode<K, V>) node;
        //若不相等(度为二节点)交换willNode,node维护的链表指针
        if (willNode != node) {
            LinkedNode<K, V> willLinkedNode = (LinkedNode<K, V>) willNode;
            //交换prev
            LinkedNode<K, V> tmp = willLinkedNode.prev;
            willLinkedNode.prev = linkedNode.prev;
            linkedNode.prev = tmp;
            if (willLinkedNode.prev == null) {
                first = willLinkedNode;
            } else {
                willLinkedNode.prev.next = willLinkedNode;
            }
            if (linkedNode.prev == null) {
                first = linkedNode;
            } else {
                linkedNode.prev.next = linkedNode;
            }
            //交换next
            tmp = willLinkedNode.next;
            willLinkedNode.next = linkedNode.next;
            linkedNode.next = tmp;
            if (willLinkedNode.next == null) {
                last = willLinkedNode;
            } else {
                willLinkedNode.next.prev = willLinkedNode;
            }
            if (linkedNode.next == null) {
                last = linkedNode;
            } else {
                linkedNode.next.prev = linkedNode;
            }
        }
        LinkedNode<K, V> prev = linkedNode.prev;
        LinkedNode<K, V> next = linkedNode.next;
        //判断被删除是头节点
        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
        }
        //判断被删除是尾节点
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
        }
    }

    private static class LinkedNode<K, V> extends MapNode<K, V> {
        //定义上一个对象指针
        LinkedNode<K, V> prev;
        //定义下一个对象指针
        LinkedNode<K, V> next;

        public LinkedNode(K key, V value, MapNode<K, V> parent) {
            super(key, value, parent);
        }
    }
}
