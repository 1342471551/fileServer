package com.leetcode.exercise;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wangyj
 * @Description: (亲密字符串)
 * https://leetcode-cn.com/problems/buddy-strings/
 * @date 2021/11/23 10:10
 */
public class _859亲密字符串 {

    public static boolean buddyStrings(String s, String goal) {
        if (s.length() != goal.length()) return false;
        int sum = 0;
        Set<Character> set = new HashSet<>();
        Set<Character> res = new HashSet<>();
        Set<Character> ddd = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != goal.charAt(i)) {
                sum++;
                set.add(s.charAt(i));
                set.add(goal.charAt(i));
                ddd.add(s.charAt(i));
            }
            res.add(s.charAt(i));
        }
        if (set.size() < 2 && ddd.size() != 1 && res.size() < s.length()) return true;
        return set.size() == 2 && ddd.size() != 1 && sum == 2;
    }

    public static void main(String[] args) {
        System.out.println(buddyStrings("abcaa", "abcbb"));
    }
}
