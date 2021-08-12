package com.leetcode.algorithms;

/**
 * @author wangyj
 * @Description: (翻转字符串里的单词)
 * https://leetcode-cn.com/problems/reverse-words-in-a-string/
 * @date 2021/6/21 11:06
 */
public class _151翻转字符串里的单词 {

    public static String reverseWords(String s) {
        if (s == null) return "";
        char[] chars = s.toCharArray();
        //消除多余空格
        int len = 0;
        int cur = 0;
        //判断前一个字符是否为空格
        boolean space = true;
        for (int i = 0; i < s.length(); i++) {
            if (chars[i] != ' ') {
                //非空格字符
                chars[cur++] = chars[i];
                space = false;
            } else if (space == false) {
                //空格字符
                chars[cur++] = ' ';
                space = true;
            }
        }
        len = space ? (cur - 1) : cur;
        if (len <= 0) return "";
        //进行字符串反转
        reverse(chars, 0, len);
        //进行单词的逆序
        int prevSpace = -1;
        for (int i = 0; i < len; i++) {
            if (chars[i] != ' ') continue;
            reverse(chars, prevSpace + 1, i);
            prevSpace = i;
        }
        //反转最后一个单词
        reverse(chars, prevSpace + 1, len);
        return new String(chars, 0, len);

    }

    //进行chars字符串的翻转
    private static void reverse(char[] chars, int li, int ri) {
        ri--;
        while (li < ri) {
            char tmp = chars[li];
            chars[li] = chars[ri];
            chars[ri] = tmp;
            li++;
            ri--;
        }
    }


    public static void main(String[] args) {
        reverseWords("the sky is blue");
    }
}
