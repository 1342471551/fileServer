package com.leetcode.exercise;

import java.util.Stack;

/**
 * @author wangyj
 * @Description: (反转每对括号间的子串)
 * https://leetcode-cn.com/problems/reverse-substrings-between-each-pair-of-parentheses/
 * @date 2021/5/26 10:19
 */
public class _1190反转每对括号间的子串 {

    public String reverseParentheses(String s) {

        StringBuilder sb = new StringBuilder();
        char[] arr = s.toCharArray();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] == '(')
                stack.push(i);

            if (arr[i] == ')')
                //通过栈的先入后出从最里面开始依次把括号内的元素反转
                reverse(arr, stack.pop() + 1, i - 1);
        }

        for (int i = 0; i < arr.length; i++)
            if (arr[i] != ')' && arr[i] != '(')
                sb.append(arr[i]);

        return sb.toString();
    }

    public void reverse(char[] arr, int left, int right) {
        while (right > left) {
            char tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
            right--;
            left++;
        }
    }
}
