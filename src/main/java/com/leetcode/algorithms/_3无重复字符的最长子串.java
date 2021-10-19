package com.leetcode.algorithms;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangyj
 * @Description: (无重复字符的最长子串)
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 * @date 2021/6/21 15:50
 */
public class _3无重复字符的最长子串 {

    public int lengthOfLongestSubstring(String s) {
        if (s == null) return 0;
        char[] chars = s.toCharArray();
        if (chars.length == 0) return 0;
        //用来保存上一次字符出现的位置
        Map<Character, Integer> preIdx = new HashMap<>();
        preIdx.put(chars[0], 0);
        //li=i-1位置结尾的最长不重复字符串的开始索引
        int li = 0;
        int max = 1;
        for (int i = 1; i < chars.length; i++) {
            //pi=这个字符上一次出现的位置
            Integer pi = preIdx.get(chars[i]);
            if (pi != null && li <= pi) {
                li = pi + 1;
            }
            //存储这个位置的元素
            preIdx.put(chars[i], i);
            max = Math.max(max, i - li + 1);
        }
        return max;
    }
}