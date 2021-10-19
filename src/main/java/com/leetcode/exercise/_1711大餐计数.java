package com.leetcode.exercise;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangyj
 * @Description: (大餐计数)
 * https://leetcode-cn.com/problems/count-good-meals/
 * @date 2021/7/7 8:45
 */
public class _1711大餐计数 {

    public static int countPairs(int[] deliciousness) {
        final int MOD = 1000000007;
        int maxVal = 0;
        for (int val : deliciousness) {
            maxVal = Math.max(maxVal, val);
        }
        int maxSum = maxVal * 2;
        int pairs = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int n = deliciousness.length;
        for (int i = 0; i < n; i++) {
            int val = deliciousness[i];
            for (int sum = 1; sum <= maxSum; sum <<= 1) {
                int count = map.getOrDefault(sum - val, 0);
                pairs = (pairs + count) % MOD;
            }
            map.put(val, map.getOrDefault(val, 0) + 1);
        }
        return pairs;
    }

    public static int countPairs1(int[] deliciousness) {
        final int MOD = 1000000007;
        if (deliciousness == null || deliciousness.length == 0) return 0;
        int maxValue = Arrays.stream(deliciousness).max().getAsInt();
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int i = 0; i < deliciousness.length; i++) {
            int val = deliciousness[i];
            for (int j = 1; j <= maxValue * 2; j <<= 1) {
                res = (res + map.getOrDefault(j - val, 0)) % MOD;
            }
            map.put(val, map.getOrDefault(val, 0) + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        countPairs1(new int[]{1, 3, 5, 7, 9});
    }

}
