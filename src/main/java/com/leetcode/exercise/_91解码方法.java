package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (解码方法)
 * https://leetcode-cn.com/problems/decode-ways/
 * @date 2021/4/21 16:58
 */
public class _91解码方法 {
    public static int numDecodings(String s) {
        int n = s.length();
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            if (s.charAt(i - 1) != '0') {
                f[i] += f[i - 1];
            }
            //charAt默认返回的是字符串,当使用加减运算的时候才会返回对应的数字
            if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26)) {
                f[i] += f[i - 2];
            }
        }
        return f[n];
    }



    public static void main(String[] args) {
        System.out.println(numDecodings("1246256"));
    }
}
