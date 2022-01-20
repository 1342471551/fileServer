package com.leetcode.linkedlist;

/**
 * @author wangyj
 * @Description: (https : / / leetcode - cn.com / problems / reverse - linked - list /)
 * @date 2021/3/29 17:52
 */
public class _206反转链表 {
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

    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newNode = reverseList1(head.next);
        //head.next是原来反转前的node,现在把他反转就是他的next指向原来的node
        head.next.next = head;
        //原来的头节点是尾部了,head.next应该指向空
        head.next = null;
        return newNode;
    }

    public ListNode reverseList2(ListNode head) {
        //定义新的链表
        ListNode newNode = null;
        while (head != null) {
            //存放临时节点
            ListNode tmp = head.next;
            head.next = newNode;
            newNode = head;
            head = tmp;
        }
        return newNode;
    }
}
