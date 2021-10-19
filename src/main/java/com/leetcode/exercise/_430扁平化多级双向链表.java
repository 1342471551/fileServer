package com.leetcode.exercise;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author wangyj
 * @Description: (扁平化多级双向链表)
 * https://leetcode-cn.com/problems/flatten-a-multilevel-doubly-linked-list/
 * @date 2021/9/24 9:17
 */
public class _430扁平化多级双向链表 {

    public Node flatten(Node head) {
        if (head == null) return null;
        LinkedList<Node> stack = new LinkedList<>();
        Node cur = head;
        while (true) {
            if (cur.child != null) {
                // 将 next 节点入栈
                if (cur.next != null) {
                    stack.push(cur.next);
                }
                // 将子链表扁平化
                cur.next = cur.child;
                cur.child.prev = cur;
                cur.child = null;
            }
            // 遍历子链表的下一个节点或是从栈中弹出 next 节点
            if (cur.next != null) {
                cur = cur.next;
            } else if (!stack.isEmpty()) {
                //把栈中取出的Node加入cur列表尾部,并且移动指针到下一个
                Node next = stack.pop();
                cur.next = next;
                next.prev = cur;
                cur = next;
            } else {
                return head;
            }
        }
    }

    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }
}
