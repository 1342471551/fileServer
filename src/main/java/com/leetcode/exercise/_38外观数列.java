package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (外观数列)
 * https://leetcode-cn.com/problems/count-and-say/
 * @date 2021/10/15 9:42
 */
public class _38外观数列 {

    public String countAndSay(int n) {
        String str = "1";
        for (int i = 2; i <= n; ++i) {
            StringBuilder sb = new StringBuilder();
            int start = 0;
            int pos = 0;

            while (pos < str.length()) {
                while (pos < str.length() && str.charAt(pos) == str.charAt(start)) {
                    pos++;
                }
                sb.append(Integer.toString(pos - start)).append(str.charAt(start));
                start = pos;
            }
            str = sb.toString();
        }
        return str;
    }
}
