package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (只出现一次的数字II)
 * https://leetcode-cn.com/problems/single-number-ii/
 * @date 2021/4/30 13:43
 */
public class _137只出现一次的数字II {
    //数组中每个数都相当于二级制中的位数,对每一位进行与运算计数非三次则说明是单独出现元素进行|累加
    public static int singleNumber(int[] nums) {
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            int mask = 1 << i;
            int cnt = 0;
            for (int j = 0; j < nums.length; j++) {
                if ((nums[j] & mask) != 0) {
                    cnt++;
                }
            }
            if (cnt % 3 != 0) {
                // ret |= mask 等价 ret = ret | mask;
                ret |= mask;
            }
        }
        return ret;
    }
}
