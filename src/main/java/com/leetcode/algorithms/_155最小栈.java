package com.leetcode.algorithms;

/**
 * @author wangyj
 * @Description: (最小栈)
 * https://leetcode-cn.com/problems/min-stack/
 * @date 2021/7/12 11:51
 */
public class _155最小栈 {

    //使用链表Node节点的形式实现代码

    private Node head;

    public _155最小栈() {
        head = new Node(0, Integer.MAX_VALUE, null);
    }

    public void push(int val) {
        //创建新节点,下一个指向原来旧的头节点
        head = new Node(val, Math.min(val, head.min), head);
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }


    private static class Node {
        private int val;
        private int min;
        private Node next;

        public Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }


//    //使用双栈的解决方式
//
//    //正常栈
//    private Stack<Integer> stack1;
//    //最小栈
//    private Stack<Integer> stack2;
//
//    /**
//     * initialize your data structure here.
//     */
//    public _155最小栈() {
//        stack1 = new Stack<>();
//        stack2 = new Stack<>();
//    }
//
//    public void push(int val) {
//        stack1.push(val);
//        if (stack2.isEmpty() || val < stack2.peek()) {
//            stack2.push(val);
//        } else {
//            stack2.push(stack2.peek());
//        }
//    }
//
//    public void pop() {
//        stack1.pop();
//        stack2.pop();
//    }
//
//    public int top() {
//        return stack1.peek();
//    }
//
//    public int getMin() {
//        return stack2.peek();
//    }
}
