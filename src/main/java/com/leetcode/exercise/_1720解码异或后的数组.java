package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (解码异或后的数组)
 * https://leetcode-cn.com/problems/decode-xored-array/
 * @date 2021/5/6 18:05
 */
public class _1720解码异或后的数组 {

    public int[] decode(int[] encoded, int first) {
        int[] nums = new int[encoded.length+1];
        nums[0] = first;
        for (int i = 0; i < encoded.length; i++) {
            nums[i+1] = encoded[i] ^ nums[i];
        }
        return nums;
    }
}
