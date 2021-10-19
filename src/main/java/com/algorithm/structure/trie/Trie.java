package com.algorithm.structure.trie;

import com.algorithm.structure.hash.HashMap;

/**
 * @author wangyj
 * @Description: (按照字符存储的索引结构)
 * @date 2021/4/25 11:34
 */
public class Trie<V> {

    private int size;
    private Node<V> root;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
        root = null;
    }

    public V get(String key) {
        Node<V> node = node(key);
        return node == null ? null : node.value;
    }

    public boolean contains(String key) {
        return node(key) != null;
    }

    public V add(String key, V value) {
        keyCheck(key);
        //创建根节点
        if (root == null) {
            root = new Node<>(null);
        }
        Node<V> node = this.root;
        int len = key.length();
        for (int i = 0; i < len; i++) {
            char c = key.charAt(i);
            boolean emptyChildren = node.childNode == null;
            Node<V> childNode = emptyChildren ? null : node.childNode.get(c);
            if (childNode == null) {
                childNode = new Node<>(node);
                //设置节点字符
                childNode.character = c;
                node.childNode = emptyChildren ? new HashMap<>() : node.childNode;
                node.childNode.put(c, node);
            }
            node = childNode;
        }
        if (!node.word) {
            //新增单词
            node.word = true;
            node.value = value;
            size++;
            return null;
        }
        V oldValue = node.value;
        node.value = value;
        size++;
        return oldValue;
    }

    public V remove(String key) {
        Node<V> node = node(key);
        //如果不是单词结尾则不处理
        if (node == null || !node.word) return null;
        size--;
        V oldValue = node.value;
        //如果还有子节点 则改变标识
        if (node.childNode != null && !node.childNode.isEmpty()) {
            node.word = false;
            node.value = null;
            return oldValue;
        }
        //没有子节点 从后向前删除发现有子节点则结束
        Node<V> parent = null;
        while ((parent = node.parent) != null) {
            parent.childNode.remove(node.character);
            //父节点为红色或父节点不为空则停止
            if (parent.word || !parent.childNode.isEmpty()) break;
            node = parent;
        }
        return oldValue;
    }

    //是否包含此前缀字符
    public boolean startsWith(String prefix) {
        keyCheck(prefix);
        Node<V> node = root;
        int len = prefix.length();
        for (int i = 0; i < len; i++) {
            if (node == null || node.childNode == null || node.childNode.isEmpty()) return false;
            char c = prefix.charAt(i);
            node = node.childNode.get(c);
        }
        return true;
    }

    private Node<V> node(String key) {
        if (root == null) return null;
        keyCheck(key);
        Node<V> node = root;
        int len = key.length();
        for (int i = 0; i < len; i++) {
            if (node == null || node.childNode == null || node.childNode.isEmpty()) return null;
            char c = key.charAt(i);
            node = node.childNode.get(c);
        }
        return node.word ? node : null;
    }

    private void keyCheck(String key) {
        if (key == null || key.length() == 0) {
            throw new IllegalArgumentException("key must not be empty");
        }
    }


    private static class Node<V> {
        //删除时用到
        Node<V> parent;
        Character character;
        HashMap<Character, Node<V>> childNode;
        V value;
        //判断到此节点是否是一个单词
        boolean word;

        public Node(Node<V> parent) {
            this.parent = parent;
        }
    }

}
