package com.leetcode.exercise;

import java.util.List;

/**
 * @author wangyj
 * @Description: (通过删除字母匹配到字典里最长单词)
 * https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting/
 * @date 2021/9/14 8:47
 */
public class _524通过删除字母匹配到字典里最长单词 {

    public String findLongestWord(String s, List<String> dictionary) {
        String res = "";
        for (String t : dictionary) {
            int i = 0, j = 0;
            while (i < t.length() && j < s.length()) {
                if (t.charAt(i) == s.charAt(j)) {
                    i++;
                }
                j++;
            }
            if (i == t.length()) {
                //确定此字符串匹配成功,并且按照字典排序输出
                if (t.length() > res.length() || (t.length() == res.length() && t.compareTo(res) < 0)) {
                    res = t;
                }
            }
        }
        return res;
    }
}
