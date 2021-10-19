package com.leetcode.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangyj
 * @Description: (括号生成)
 * https://leetcode-cn.com/problems/generate-parentheses/
 * @date 2021/6/30 9:29
 */
public class _22括号生成 {

    Map<String, Integer> map;
    List<String> res;
    char[] chars;

    public List<String> generateParenthesis(int n) {
        if (n == 0) return null;
        map = new HashMap<>();
        map.put("(", n);
        map.put(")", n);
        res = new ArrayList<>();
        chars = new char[2 * n];
        dfs(0);
        return res;
    }

    private void dfs(int idx) {
        if (idx == chars.length - 1) {
            Integer integer = map.get("(");
            if (integer > 0) chars[idx] = '(';
            else chars[idx] = ')';
            res.add(new String(chars));
            return;
        }

        if (map.get("(").equals(map.get(")"))) {
            map.put("(", map.get("(") - 1);
            chars[idx] = '(';
            dfs(idx + 1);
            map.put("(", map.get("(") + 1);
        } else {
            for (int i = 0; i < 2; i++) {
                if (map.get("(") > 0 && i == 0) {
                    map.put("(", map.get("(") - 1);
                    chars[idx] = '(';
                    dfs(idx + 1);
                    map.put("(", map.get("(") + 1);
                }
                if (map.get(")") > 0 && i == 1) {
                    map.put(")", map.get(")") - 1);
                    chars[idx] = ')';
                    dfs(idx + 1);
                    map.put(")", map.get(")") + 1);
                }
            }
        }
    }


    public static void main(String[] args) {
        _22括号生成 s = new _22括号生成();
        System.out.println(s.generateParenthesis(3));
    }
}
