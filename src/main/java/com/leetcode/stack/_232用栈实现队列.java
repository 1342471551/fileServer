package com.leetcode.stack;

import java.util.Queue;
import java.util.Stack;

/**
 * @author wangyj
 * @Description: (https : / / leetcode - cn.com / problems / implement - queue - using - stacks /)
 * @date 2021/4/1 10:49
 */
public class _232用栈实现队列 {
    /**
     * 使用两个栈in/out->Stack来实现
     * 入队放到inStack,出队弹出outStack元素
     * OutStack为空则把inStack元素全部放入
     */

    Stack<Integer> inStack = null;
    Stack<Integer> outStack = null;

    /**
     * Initialize your data structure here.
     */
    public _232用栈实现队列() {
        inStack = new Stack<Integer>();
        outStack = new Stack<Integer>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        inStack.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        checkOutStack();
        return outStack.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        checkOutStack();
        return outStack.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        checkOutStack();
        return outStack.isEmpty();
    }

    private void checkOutStack() {
        if (outStack.empty()) {
            while (!inStack.empty()) {
                outStack.push(inStack.pop());
            }
        }
    }
}
