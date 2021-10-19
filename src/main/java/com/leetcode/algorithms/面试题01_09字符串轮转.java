package com.leetcode.algorithms;

/**
 * @author wangyj
 * @Description: (字符串轮转)
 * https://leetcode-cn.com/problems/string-rotation-lcci/
 * @date 2021/7/16 16:07
 */
public class 面试题01_09字符串轮转 {

    public boolean isFlipedString(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        if (s1.length() != s2.length()) return false;
        //KMP算法
        return (s1 + s1).contains(s2);
    }
}
