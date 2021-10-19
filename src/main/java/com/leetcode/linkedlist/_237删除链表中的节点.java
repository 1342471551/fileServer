package com.leetcode.linkedlist;

/**
 * @author wangyj
 * @Description: (https://leetcode-cn.com/problems/delete-node-in-a-linked-list/)
 * @date 2021/3/29 17:42
 */
public class _237删除链表中的节点 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

}
