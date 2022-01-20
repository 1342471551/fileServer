package com.leetcode.algorithms;

import java.util.Arrays;
import java.util.function.IntPredicate;

/**
 * @author wangyj
 * @Description: (有效的字母异位词)
 * https://leetcode-cn.com/problems/valid-anagram/
 * @date 2021/7/16 17:17
 */
public class _242有效的字母异位词 {

    public boolean isAnagram(String s, String t) {
        if (s == null || t == null) return false;
        if (s.length() != t.length()) return false;
        int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
            counts[t.charAt(i) - 'a']--;
        }
        return Arrays.stream(counts).allMatch(new IntPredicate() {
            @Override
            public boolean test(int value) {
                return value == 0;
            }
        });
    }
}
