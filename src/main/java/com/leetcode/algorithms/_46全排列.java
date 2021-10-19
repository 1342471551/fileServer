package com.leetcode.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangyj
 * @Description: (全排列)
 * https://leetcode-cn.com/problems/permutations/
 * @date 2021/6/29 11:05
 */
public class _46全排列 {

    private List<List<Integer>> res;
    private Map<Integer, Boolean> map;
    private int[] result;

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        res = new ArrayList<>();
        map = new HashMap<Integer, Boolean>();
        for (Integer num : nums) {
            map.put(num, true);
        }
        result = new int[nums.length];
        dfs(0);
        return res;
    }

    private void dfs(int idx) {
        if (idx == map.size()) {
            List<Integer> list = new ArrayList<>();
            for (int value : result) {
                list.add(value);
            }
            res.add(list);
            return;
        }

        map.forEach((v1, v2) -> {
            if (v2) {
                result[idx] = v1;
                map.put(v1, false);
                dfs(idx + 1);
                map.put(v1, true);
            }
        });
    }
}
