package com.leetcode.algorithms;

/**
 * @author wangyj
 * @Description: (分隔链表)
 * https://leetcode-cn.com/problems/partition-list/
 * @date 2021/7/9 14:52
 */
public class _86分隔链表 {

    public ListNode partition(ListNode head, int x) {
        if (head == null) return null;
        ListNode lHead = new ListNode();
        ListNode lTail = lHead;
        ListNode rHead = new ListNode();
        ListNode rTail = rHead;
        while (head != null) {
            if (head.val < x) {
                lTail.next = new ListNode(head.val);
                lTail = lTail.next;
            } else {
                rTail.next = new ListNode(head.val);
                rTail = rTail.next;
            }
            head = head.next;
        }
        lTail.next = rHead.next;
        return lHead.next;
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
