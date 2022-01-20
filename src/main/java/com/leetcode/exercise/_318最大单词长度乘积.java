package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (最大单词长度乘积)
 * https://leetcode-cn.com/problems/maximum-product-of-word-lengths/
 * @date 2021/11/17 4:23 下午
 */
public class _318最大单词长度乘积 {

    public int maxProduct(String[] words) {
        /**
         全是小写字母, 可以用一个32为整数表示一个word中出现的字母,
         hash[i]存放第i个单词出现过的字母, a对应32位整数的最后一位,
         b对应整数的倒数第二位, 依次类推. 时间复杂度O(N^2)
         判断两两单词按位与的结果, 如果结果为0且长度积大于最大积则更新
         **/
        int n = words.length;
        int[] hash = new int[n];
        int max = 0;
        for (int i = 0; i < n; ++i) {
            for (char c : words[i].toCharArray()) {
                //使用前26位每一位代表一个字母0-a，b-1 ··· 要是最后取与运算不为0说明有重复的数字
                hash[i] |= 1 << (c - 'a');
            }
        }

        for (int i = 0; i < n - 1; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if ((hash[i] & hash[j]) == 0) {
                    max = Math.max(words[i].length() * words[j].length(), max);
                }
            }
        }
        return max;
    }


    public int maxProduct1(String[] words) {
        int len = words.length;
        int[] hash = new int[len];
        for (int i = 0; i < len; i++) {
            for (char c : words[i].toCharArray()) {
                hash[i] |= 1 << (c - 'a');
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if ((hash[i] & hash[j]) == 0) {
                    max = Integer.max(max, words[i].length() * words[j].length());
                }
            }
        }
        return max;
    }


}
