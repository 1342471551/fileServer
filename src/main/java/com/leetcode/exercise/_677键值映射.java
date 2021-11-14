package com.leetcode.exercise;

import com.algorithm.structure.stack.Stack;

/**
 * @author wangyj
 * @Description: (键值映射)
 * https://leetcode-cn.com/problems/map-sum-pairs/
 * @date 2021/11/14 1:47 下午
 */
public class _677键值映射 {

    Node node;

    public _677键值映射() {
        node = new Node();
    }

    public void insert(String key, int val) {
        Node node = this.node;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            node = node.set((int) c - 97);
        }
        node.var = val;
    }

    public int sum(String prefix) {
        Node node = this.node;
        for (int i = 0; i < prefix.length(); i++) {
            node = node.get((int) prefix.charAt(i) - 97);
            if (node == null) {
                return 0;
            }
        }
        int sum = 0;
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            sum += pop.var;
            for (Node n : pop.children) {
                if (n != null) {
                    stack.push(n);
                }
            }
        }
        return sum;
    }


    class Node {
        int var = 0;
        Node[] children = new Node[26];

        public Node set(int key) {
            if (children[key] == null) {
                children[key] = new Node();
            }
            return children[key];
        }

        public Node get(int key) {
            return children[key];
        }
    }

    public static void main(String[] args) {
        _677键值映射 s=new _677键值映射();
        s.insert("apple",3);
        s.insert("app",2);
        System.out.println(s.sum("ap"));
    }
}
