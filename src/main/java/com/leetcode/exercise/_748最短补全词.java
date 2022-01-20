package com.leetcode.exercise;

import java.util.Arrays;

/**
 * @author wangyj
 * @Description: (最短补全词)
 * https://leetcode-cn.com/problems/shortest-completing-word/
 * @date 2021/12/10 10:43
 */
public class _748最短补全词 {

    public String shortestCompletingWord(String licensePlate, String[] words) {
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        int[] arr = new int[26];
        int sum = 0;
        //把大写字母全部转换成小写
        for (char ch : licensePlate.toLowerCase().toCharArray()) {
            //判断是字母存入对应的数组中
            if (Character.isLowerCase(ch)) {
                arr[ch - 'a']++;
                //记录有效字母数
                sum++;
            }
        }
        for (String s : words) {
            int[] arr_ = Arrays.copyOf(arr, arr.length);
            int sum_ = 0;
            char[] chs = s.toCharArray();
            for (char ch : chs) {
                if (arr_[ch - 'a'] > 0) {
                    arr_[ch - 'a']--;
                    sum_++;
                }
                if (sum_ == sum) return s;
            }
        }
        return "煞笔";
    }
}
