package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (形成两个异或相等数组的三元组数目)
 * https://leetcode-cn.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/
 * @date 2021/5/18 20:28
 */
public class _1442形成两个异或相等数组的三元组数目 {

    //两个相等的数进行异或运算必定是0，a、b两个数相等，所以需要求 i 到 k 的数异或为 0 的区间，
    //因为 j > i，k >= j， 所以 k 必须大于 i，只要有异或为0的区间，就相加
    public int countTriplets1(int[] arr) {
        int len = arr.length;
        int ans = 0;

        for (int i = 0; i < len - 1; i++) {
            int sum = 0;
            for (int k = i; k < len; k++) {
                sum ^= arr[k];
                if (sum == 0 && k > i) {
                    //j可以取ik间的任意位置
                    ans += (k - i);
                }
            }
        }
        return ans;
    }
}
