package com.leetcode.algorithms;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangyj
 * @Description: (LRU缓存机制)
 * https://leetcode-cn.com/problems/lru-cache/
 * 操作系统页面置换算法,最近最少使用页面进行淘汰
 * @date 2021/7/7 16:44
 */
public class _146LRU缓存机制 {

    Map<Integer, Node> map;
    private int capacity;
    //虚拟头节/尾点
    private Node first;
    private Node last;

    public _146LRU缓存机制(int capacity) {
        map = new HashMap<>(capacity);
        this.capacity = capacity;
        first = new Node();
        last = new Node();
        first.next = last;
        last.prev = first;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;

        removeNode(node);
        addAfterNode(node);

        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        //判断key是否存在 进行更新
        if (node != null) {
            node.value = value;
            removeNode(node);
            addAfterNode(node);
        } else {
            if (map.size() == capacity) {
                //进行淘汰操作
                removeNode(map.remove(last.prev.key));
            }
            map.put(key, node = new Node(key, value));
            addAfterNode(node);
        }
    }

    //删除节点
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    //把此节点插入头部
    private void addAfterNode(Node node) {
        //先连右边的线,之后左边的(防止找不到右边的节点)
        node.next = first.next;
        first.next.prev = node;
        node.prev = first;
        first.next = node;
    }


    private static class Node {
        public int key;
        public int value;
        public Node prev;
        public Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public Node() {
        }
    }
}
