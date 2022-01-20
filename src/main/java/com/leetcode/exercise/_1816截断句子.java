package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (截断句子)
 * https://leetcode-cn.com/problems/truncate-sentence/
 * @date 2021/12/6 9:17
 */
public class _1816截断句子 {

    public String truncateSentence(String s, int k) {
        int index = s.length();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                k--;
                if (k <= 0) {
                    index = i;
                    break;
                }
            }
        }
        return s.substring(0, index);
    }
}
