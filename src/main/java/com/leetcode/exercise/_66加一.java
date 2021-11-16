package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (加一)
 * https://leetcode-cn.com/problems/plus-one/
 * @date 2021/10/21 8:53
 */
public class _66加一 {

    public static int[] plusOne(int[] digits) {
        int digit = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] + digit > 9) {
                sb.append(digits[i] + digit - 10);
                digit = 1;
            } else {
                sb.append(digits[i] + digit);
                digit = 0;
            }
        }
        if (digit == 1) {
            sb.append(digit);
        }
        String value = sb.toString();
        int[] res = new int[value.length()];
        for (int i = 0; i < value.length(); i++) {
            //减去48转换成正常的整数
            res[i] = value.charAt(value.length() - i - 1) - 48;
        }
        return res;
    }

    public int[] plusOne1(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        //跳出for循环，说明数字全部是9
        int[] temp = new int[digits.length + 1];
        temp[0] = 1;
        return temp;
    }
}
