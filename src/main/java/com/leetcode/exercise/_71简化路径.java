package com.leetcode.exercise;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author wangyj
 * @Description: (简化路径)
 * https://leetcode-cn.com/problems/simplify-path/
 * @date 2022/1/6 10:56
 */
public class _71简化路径 {

    /**
     * 给定的字符串根据 {/}分割成一个由若干字符串组成的列表，记为names
     * 空字符串 例如当出现多个连续的/就会分割出空字符串
     * 一个点.
     * 两个点..
     * 只包含英文字母、数字或\的目录名
     */
    public String simplifyPath(String path) {
        String[] names = path.split("/");
        Deque<String> stack = new ArrayDeque<String>();
        for (String name : names) {
            if ("..".equals(name)) {
                if (!stack.isEmpty()) {
                    stack.pollLast();
                }
            } else if (name.length() > 0 && !".".equals(name)) {
                stack.offerLast(name);
            }
        }
        StringBuffer ans = new StringBuffer();
        if (stack.isEmpty()) {
            ans.append('/');
        } else {
            while (!stack.isEmpty()) {
                ans.append('/');
                ans.append(stack.pollFirst());
            }
        }
        return ans.toString();
    }

    public String simplifyPath1(String path) {
        String[] split = path.split("/");
        Deque<String> deque = new ArrayDeque<>();
        for (String name : split) {
            if (name.equals("..")) {
                if (!deque.isEmpty()) {
                    deque.pollLast();
                }
            } else if (name.length() > 0 && !name.equals(".")) {
                deque.offerLast(name);
            }
        }
        StringBuffer buffer = new StringBuffer();
        if (deque.isEmpty()) {
            buffer.append('/');
        } else {
            while (!deque.isEmpty()) {
                buffer.append('/');
                buffer.append(deque.pollFirst());
            }
        }
        return new String(buffer);
    }
}
