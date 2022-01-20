package com.leetcode.queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author wangyj
 * @Description: (用队列实现栈)
 * https://leetcode-cn.com/problems/implement-stack-using-queues/
 * @date 2021/4/1 18:22
 */
public class _225用队列实现栈 {

    /**
     * 思路:
     * 使用双向队列,操作队列队尾元素实现入栈出栈功能
     */

    Deque<Integer> queue = null;

    public _225用队列实现栈() {
        queue = new ArrayDeque<Integer>();
    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        queue.offerLast(x);
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        return queue.removeLast();
    }

    /**
     * Get the top element.
     */
    public int top() {
        return queue.getLast();
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return queue.isEmpty();
    }
}
