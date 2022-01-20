package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (反转字符串)
 * https://leetcode-cn.com/problems/reverse-string-ii/
 * @date 2021/8/20 16:55
 */
public class _541反转字符串II {

    /**
     * 每隔k个反转k个，末尾不够k个时全部反转
     */
    public String reverseStr(String s, int k) {
        if (s.length() <= 1) return s;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i += 2 * k) {
            if (i + k <= chars.length) {
                reverse(chars, i, i + k);
            } else {
                reverse(chars, i, chars.length);
            }
        }
        StringBuilder builder = new StringBuilder();
        for (char c : chars) {
            builder.append(c);
        }
        return builder.toString();
    }

    //左闭右开
    private void reverse(char[] s, int begin, int end) {
        while (begin <= end - 1) {
            char tmp = s[begin];
            s[begin] = s[end - 1];
            s[end - 1] = tmp;
            begin++;
            end--;
        }
    }

    public static void main(String[] args) {
        _541反转字符串II s = new _541反转字符串II();
        s.reverseStr("abcdefg", 2);
    }
}
