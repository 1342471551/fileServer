package com.algorithm.structure.union;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author wangyj
 * @Description: (并查集)
 * @date 2021/5/7 11:24
 */
public class GenericUnionFind<V> {

    private Map<V, Node<V>> nodes = new HashMap<>();

    public void makeSet(V v) {
        if (nodes.containsKey(v)) return;
        nodes.put(v, new Node<V>(v));
    }

    public V find(V v) {
        Node<V> node = findNode(v);
        return node == null ? null : node.value;
    }

    public void union(V v1, V v2) {
        Node<V> p1 = findNode(v1);
        Node<V> p2 = findNode(v2);
        if (p1 == null || p2 == null) return;
        if (Objects.equals(p1.value, p2.value)) return;
        if (p1.rank < p2.rank) {
            p1.parent = p2;
        } else if (p1.rank > p2.rank) {
            p2.parent = p1;
        } else {
            //高度相同,把p1指向p2高度加1
            p1.parent = p2;
            p2.rank += 1;
        }
    }

    public boolean isSame(V v1, V v2) {
        return Objects.equals(find(v1), find(v2));
    }

    //找到传入v的根节点node
    private Node<V> findNode(V v) {
        Node<V> node = nodes.get(v);
        if (node == null) return null;
        while (!Objects.equals(node.value, node.parent.value)) {
            node.parent = node.parent.parent;
            node = node.parent;
        }
        return node;
    }

    private static class Node<V> {
        V value;
        //默认节点指向自己
        Node<V> parent = this;
        //默认树高度为1
        int rank = 1;

        public Node(V value) {
            this.value = value;
        }
    }
}
