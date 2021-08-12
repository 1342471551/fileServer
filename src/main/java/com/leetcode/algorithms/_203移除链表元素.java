package com.leetcode.algorithms;

/**
 * @author wangyj
 * @Description: (移除链表元素)
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 * @date 2021/7/9 11:12
 */
public class _203移除链表元素 {

    public ListNode removeElements(ListNode head, int val) {
        ListNode newHead = new ListNode();
        newHead.next = head;
        ListNode curr = newHead;
        while (curr.next != null) {
            if (curr.next.val==val){
                curr.next=curr.next.next;
            }else {
                curr=curr.next;
            }
        }
        return newHead.next;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
