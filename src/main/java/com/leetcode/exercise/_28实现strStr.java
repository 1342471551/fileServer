package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (实现strStr)
 * https://leetcode-cn.com/problems/implement-strstr/
 * @date 2021/4/20 17:07
 */
public class _28实现strStr {
    public static int strStr(String haystack, String needle) {
        int a = haystack.length();
        int b = needle.length();
        if (b == 0) return 0;
        if (a == 0 || a < b) return -1;
        for (int i = 0; i < a - b + 1; i++) {
            int index = 0;
            do {
                if (haystack.charAt(i+index) == needle.charAt(index)) {
                    index++;
                }else{
                    break;
                }
            } while (index < b);
            if (index==b) return i;
        }
        return -1;
    }

    public static int strStr1(String haystack, String needle) {
        return haystack.indexOf(needle);
    }
}
