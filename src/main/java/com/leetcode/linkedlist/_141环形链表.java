package com.leetcode.linkedlist;

/**
 * @author wangyj
 * @Description: (https : / / leetcode - cn.com / problems / linked - list - cycle /)
 * @date 2021/3/30 15:23
 */
public class _141环形链表 {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public boolean hasCycle(ListNode head) {
        //利用快慢指针 快指针一次next两步,慢指针一次一步 若两个指针相遇则有环,快指针先达到空则没有环
        if (head == null || head.next == null) return false;
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            if (fast.val == slow.val) {
                return true;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return false;
    }
}
