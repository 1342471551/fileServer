package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (数字1的个数)
 * https://leetcode-cn.com/problems/number-of-digit-one/
 * @date 2021/8/13 4:10 下午
 */
public class _233数字1的个数 {


    /**
     * 假设n=2304
     * 我们先锁住十位，强行让十位变成1，剩下三位可以随意滚动：XX1X。那么求十位出现一的个数也就是，我可以滚出多少种密码组合，使得该密码小于等于n（注意十位被锁定成了1，转不动）
     * 滚出的最大数是：2219,滚出的最小数是：0010  即十位数一共有229 - 000 + 1 = 230种
     * 其他位数同理 相加就是最终结果
     */
    public static int countDigitOne(int n) {
        int digit = 1, res = 0;
        //low是当前位后面的值
        int high = n / 10, cur = n % 10, low = 0;
        while (high != 0 || cur != 0) {
            if (cur == 0) {
                //当前位为0,一共可能是1的可能性就是去掉此位最大的数
                res += high * digit;
            } else if (cur == 1) {
                //当前位为1
                res += high * digit + low + 1;
            } else {
                //当前位大于1
                res += (high + 1) * digit;
            }
            low += cur * digit;
            cur = high % 10;
            high /= 10;
            digit *= 10;
        }
        return res;
    }

    public static void main(String[] args) {
        countDigitOne(201);
    }
}
