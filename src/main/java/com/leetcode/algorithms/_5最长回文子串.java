package com.leetcode.algorithms;

/**
 * @author wangyj
 * @Description: (最长回文子串)
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 * @date 2021/6/23 9:55
 */
public class _5最长回文子串 {


    //马拉车算法
    public static String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        //预处理字符串 头尾为不同字符(^,$),每个字符串前后为相同字符(#)
        char[] cs = new char[(s.length() << 1) + 3];
        cs[0] = '^';
        cs[1] = '#';
        cs[cs.length - 1] = '$';
        for (int i = 0; i < chars.length; i++) {
            int idx = (i + 1) << 1;
            cs[idx] = chars[i];
            cs[idx + 1] = '#';
        }
        //构建m数组(cs位置-m长度)>>1为起始回文子串位置
        int[] m = new int[cs.length];
        int c = 1, r = 1, lastIdx = m.length - 2;
        int maxLen = 0, idx = 0;
        for (int i = 2; i < lastIdx; i++) {
            if (r > i) {
                int li = (c << 1) - i;
                if (i + m[li] <= r) m[i] = m[li];
                else m[i] = r - i;
            }
            //以i为中心向左右扩展
            while (cs[i + m[i] + 1] == cs[i - m[i] - 1]) {
                m[i]++;
            }
            //更新c,r
            if (i + m[i] > r) {
                c = i;
                r = i + m[i];
            }
            //找出更大子串
            if (m[i] > maxLen) {
                maxLen = m[i];
                idx = i;
            }
        }
        int begin = (idx - maxLen) >> 1;
        return new String(chars, begin, maxLen);
    }


    /**
     * 动态规划
     * dp[i][j]=从i出发到j是否是回文子串 存储true或false
     * 初始状态当i=j时 dp[i][j]=1=true
     * 转换方程 若i,j小于等于2 则s[i]=s[j]成立 dp[i][j]=true
     * i,j大于等于3 去掉头尾中间的是回文串并且头尾相等则成立 dp[i][j]=(dp[i+1][j-1]为true+s[i]=s[j]相等)=true
     */
    public static String longestPalindrome1(String s) {
        if (s == null || s.length() == 0) return "";
        char[] chars = s.toCharArray();
        boolean[][] dp = new boolean[chars.length][chars.length];
        for (int i = 0; i < chars.length; i++) {
            dp[i][i] = true;
        }
        String max = String.valueOf(chars[0]);
        for (int i = chars.length - 1; i >= 0; i--) {
            for (int j = i + 1; j < chars.length; j++) {
                if ((j - i + 1) <= 2) {
                    if (chars[i] == chars[j]) {
                        dp[i][j] = true;
                    }
                } else {
                    if (dp[i + 1][j - 1] && chars[i] == chars[j]) {
                        dp[i][j] = true;
                    }
                }
                if (dp[i][j] && (j - i + 1) > max.length()) {
                    max = s.substring(i, j + 1);
                }
            }
        }
        return max;
    }

    //暴力
    public static String longestPalindrome2(String s) {
        if (s == null || s.length() == 0) return "";
        int length = s.length();
        char[] chars = s.toCharArray();
        String max = "";
        for (int i = 0; i < length; i++) {
            for (int j = 0; j <= i; j++) {
                String string = getString(chars, j, i);
                if (max.length() < string.length()) {
                    max = string;
                }
            }
        }
        return max;
    }

    private static String getString(char[] chars, int start, int end) {
        StringBuffer buffer = new StringBuffer();
        StringBuffer reverseBuffer = new StringBuffer();
        int len = start;
        while (len <= end) {
            buffer.append(chars[len++]);
        }
        while (end >= start) {
            reverseBuffer.append(chars[end--]);
        }
        if (buffer.toString().equals(reverseBuffer.toString())) {
            return buffer.toString();
        }
        return "";
    }

    public static void main(String[] args) {
        String s = "aaaa";
        System.out.println(longestPalindrome(s));
    }

}
