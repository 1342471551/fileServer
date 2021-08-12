package com.leetcode.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author wangyj
 * @Description: (https : / / leetcode - cn.com / problems / valid - parentheses /)
 * @date 2021/3/31 20:34
 */
public class _20有效的括号 {
    /**
     * 使用字符串替换操作进行改变
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        while (s.contains("{}") || s.contains("()") || s.contains("[]")) {
            s.replace("{}", "");
            s.replace("()", "");
            s.replace("[]", "");
        }
        return s.isEmpty();
    }


    /**
     * 使用stack进行判断
     *
     * @param s
     * @return
     */
    public boolean isValid1(String s) {
        Stack<Character> stack = new Stack<Character>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char left = stack.pop();
                if (left == '(' && c != ')') return false;
                if (left == '{' && c != '}') return false;
                if (left == '[' && c != ']') return false;
            }
        }
        return stack.isEmpty();
    }


    /**
     * 使用map进行判断
     *
     * @param s
     * @return
     */
    public boolean isValid2(String s) {
        Stack<Character> stack = new Stack<Character>();
        Map<Character, Character> map = new HashMap<Character, Character>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char pop = stack.pop();
                if (!map.get(pop).equals(c)) return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String a = "()";
        _20有效的括号 s = new _20有效的括号();
        boolean valid1 = s.isValid1(a);
        System.out.println(valid1);
    }

}

