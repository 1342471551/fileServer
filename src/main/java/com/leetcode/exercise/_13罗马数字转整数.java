package com.leetcode.exercise;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangyj
 * @Description: (罗马数字转整数)
 * https://leetcode-cn.com/problems/roman-to-integer/
 * @date 2021/5/15 15:43
 */
public class _13罗马数字转整数 {

    Map<Character, Integer> symbolValues = new HashMap<Character, Integer>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    public int romanToInt(String s) {
        int sum = 0;
        int length = s.length();
        for (int i = 0; i < length; ++i) {
            int c = symbolValues.get(s.charAt(i));
            if (i < length - 1 && c < symbolValues.get(s.charAt(i + 1))) {
                sum -= c;
            } else {
                sum += c;
            }
        }
        return sum;
    }

}
