package com.leetcode.exercise;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangyj
 * @Description: (连续数组)
 * https://leetcode-cn.com/problems/contiguous-array/
 * @date 2021/6/3 10:34
 */
public class _525连续数组 {

    public int findMaxLength(int[] nums) {
        //res:连续数组长度 sum:当前数组的和
        int res = 0, sum = 0;
        //将原数组的0全部变为-1 则问题等价于元素值总和为0的连续数组
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nums[i] = -1;
            }
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            //若当前和为0则记录res最大值
            if (sum == 0 && i > res) {
                res = i + 1;
            }
            //若map中记录了上一个sum和当前相等,则和当前相减就是这段区间和为0的和res中比较选最大的连续长度
            if (map.containsKey(sum)) {
                res = Math.max(i - map.get(sum), res);
            } else {
                map.put(sum, i);
            }
        }
        return res;
    }
}
