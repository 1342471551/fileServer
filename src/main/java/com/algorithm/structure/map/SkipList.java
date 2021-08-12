package com.algorithm.structure.map;

import java.util.Comparator;

/**
 * @author wangyj
 * @Description: (跳表)
 * @date 2021/6/16 11:16
 */
public class SkipList<K, V> implements map<K, V> {
    //最大跳表层数
    private static final int MAX_LEVEL = 32;
    //跳表随机数
    private static final double P = 0.25;
    //元素数量
    private int size;
    //传入比较器
    private Comparator<K> comparator;
    //定义虚拟首届点(不存放k,v)
    private Node<K, V> frist;
    //记录有效层数
    private int level;

    public SkipList(Comparator<K> comparator) {
        this.comparator = comparator;
        frist = new Node<>(null, null, MAX_LEVEL);
        frist.nexts = new Node[MAX_LEVEL];
    }

    public SkipList() {
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public V put(K key, V value) {
        keyCheck(key);
        Node<K, V> node = frist;
        Node<K, V>[] pres = new Node[level];
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            while ((cmp = comparator.compare(key, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }
            if (cmp == 0) {
                //链表中存在key元素,进行value替换
                V oldValue = node.nexts[i].value;
                node.nexts[i].value = value;
                return oldValue;
            }
            //保存当前层key元素的前驱节点
            pres[i] = node;
        }
        int newLevel = randomLevel();
        //到此拿到的node是添加元素的前驱节点,创建新元素
        Node<K, V> newNode = new Node<>(key, value, newLevel);
        //设置此元素的前驱或后继节点
        for (int i = 0; i < newLevel; i++) {
            if (i >= level) {
                //当此元素层数超过目前最大层数时
                frist = newNode.nexts[i];
            }
            newNode.nexts[i] = pres[i].nexts[i];
            pres[i].nexts[i] = newNode;
        }
        //元素数量
        size++;
        //跳表层数
        level = Math.max(level, newLevel);

        return null;
    }

    @Override
    //从first最上面有效层开始搜搜,找到下一个key的元素,>继续,=返回,<或搜索到队尾则退回上个元素从下一层继续搜索
    //直到最后一层都未找到则返回null
    public V get(K key) {
        keyCheck(key);
        Node<K, V> node = frist;
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            //key > node.nexts[i].key的情况 继续循环到下一个节点
            while ((cmp = comparator.compare(key, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }
            //key <= node.nexts[i].key的情况 判断是否相等
            if (cmp == 0) return node.nexts[i].value;
        }
        return null;
    }

    @Override
    public V remove(K key) {
        keyCheck(key);
        Node<K, V> node = frist;
        Node<K, V>[] pres = new Node[level];
        boolean exist = false;
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            while (node.nexts[i] != null && (cmp = compare(key, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }
            pres[i] = node;
            if (cmp == 0) exist = true;
        }
        //判断key是否存在
        if (!exist) return null;
        //拿到被删除节点
        Node<K, V> removeNode = node.nexts[0];
        //数量减少
        size--;
        //设置被删除node前驱的后继节点
        for (int i = 0; i < removeNode.nexts.length; i++) {
            pres[i].nexts[i] = removeNode.nexts[i];
        }
        //更新跳表层数(循环检测头节点当前下标指向是否为空,为空则下标--)
        int nweLevel = level;
        while (nweLevel-- >= 0 && frist.nexts[nweLevel] == null) {
            level = nweLevel;
        }
        //最后的node是key的最下面一层的前驱节点
        return removeNode.value;
    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {

    }

    private int compare(K k1, K k2) {
        return comparator != null ?
                comparator.compare(k1, k2)
                : ((Comparable<K>) k1).compareTo(k2);
    }

    private void keyCheck(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }
    }

    //返回生成节点的层数
    private int randomLevel() {
        int level = 1;
        while (Math.random() < P && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V>[] nexts;

        public Node(K key, V value, int level) {
            this.key = key;
            this.value = value;
            nexts = new Node[level];
        }
    }
}
