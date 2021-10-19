package com.leetcode.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyj
 * @Description: (电话号码的字母组合)
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 * @date 2021/6/29 10:20
 */
public class _17电话号码的字母组合 {

    private char[] chars;
    private List<String> list;
    private char[] string;

    private char[][] lettersArray = {
            {'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'},
            {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}
    };

    public List<String> letterCombinations(String digits) {
        if (digits == null) return null;
        list = new ArrayList<>();
        chars = digits.toCharArray();
        if (chars.length == 0) return list;
        string = new char[digits.length()];
        dfs(0);
        return list;
    }

    private void dfs(int idx) {
        if (idx == chars.length) {
            //得到了一个解
            list.add(new String(string));
            return;
        }
        char[] letters = lettersArray[this.chars[idx] - '2'];
        for (char letter : letters) {
            string[idx] = letter;
            dfs(idx + 1);
        }
    }
}
