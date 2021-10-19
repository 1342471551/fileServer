package com.leetcode.algorithms;

/**
 * @author wangyj
 * @Description: (回文链表)
 * https://leetcode-cn.com/problems/palindrome-linked-list/
 * @date 2021/7/9 15:22
 */
public class _234回文链表 {

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        if (head.next.next == null) return head.val == head.next.val;

        //找到中心链表
        ListNode middle = middleNode(head);
        //反转右半部分
        ListNode rHead = reverseList(middle.next);
        ListNode lHead = head;
        while (rHead != null) {
            if (lHead.val != rHead.val) return false;
            lHead = lHead.next;
            rHead = rHead.next;
        }
        return true;
    }


    //求链表中心节点(利用快慢指针)
    private ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;

    }

    //反转传入链表
    private ListNode reverseList(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode tmp = head.next;
            head.next = newHead;
            newHead = head;
            head = tmp;
        }
        return newHead;
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
