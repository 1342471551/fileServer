package com.leetcode.exercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangyj
 * @Description: (求众数II)
 * https://leetcode-cn.com/problems/majority-element-ii/
 * @date 2021/10/22 9:26
 */
public class _229求众数II {

    public List<Integer> majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        int value = nums.length / 3;
        for (int var : nums) {
            map.merge(var, 1, Integer::sum);
        }
        map.forEach((k, v) -> {
            if (v > value) list.add(k);
        });
        return list;
    }

    public static void main(String[] args) {
        _229求众数II s = new _229求众数II();
        int[] sum = new int[]{3, 2, 3};
        s.majorityElement(sum);
    }
}
