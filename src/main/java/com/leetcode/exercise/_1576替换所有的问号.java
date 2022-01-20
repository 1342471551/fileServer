package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (替换所有的问号)
 * https://leetcode-cn.com/problems/replace-all-s-to-avoid-consecutive-repeating-characters/
 * @date 2022/1/5 9:49
 */
public class _1576替换所有的问号 {

    public String modifyString(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '?') {
                for (char j = 'a'; j <= 'c'; j++) {
                    if ((i > 0 && chars[i - 1] == j) || (i < chars.length - 1 && chars[i + 1] == j)) {
                        continue;
                    }
                    chars[i] = j;
                    break;
                }
            }
        }
        return new String(chars);
    }
}
