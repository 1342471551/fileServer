package com.leetcode.exercise;

import com.algorithm.structure.stack.Stack;

/**
 * @author wangyj
 * @Description: (括号的最大嵌套深度)
 * https://leetcode-cn.com/problems/maximum-nesting-depth-of-the-parentheses/
 * @date 2022/1/7 9:18
 */
public class _1614括号的最大嵌套深度 {

    public int maxDepth(String s) {
        int max = 0;
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            if (ch == '(') {
                stack.push('(');
                max = Math.max(max, stack.size());
            } else if (ch == ')') {
                stack.pop();
            }
        }
        return max;
    }
}
