package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (链表中倒数第k个节点)
 * https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
 * @date 2021/9/2 10:47
 */
public class 剑指Offer22链表中倒数第k个节点 {

    //利用快慢指针,快指针先走k步骤 慢指针再走 当快指针到尽头的时候慢指针就是要返回的值
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode quick = head, slow = head;
        while (quick != null && k > 0) {
            quick = quick.next;
            k--;
        }
        while (quick != null) {
            quick = quick.next;
            slow = slow.next;
        }
        return slow;
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
